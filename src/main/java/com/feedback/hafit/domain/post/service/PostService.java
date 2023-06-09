package com.feedback.hafit.domain.post.service;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.service.CategoryService;
import com.feedback.hafit.domain.post.dto.reqeust.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.reqeust.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.entity.PostFile;
import com.feedback.hafit.domain.post.repository.FileImageRepository;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final UserService userService;

    private final PostRepository postRepository;

    private final CategoryService categoryService;

    private final S3Service s3Service;

    private final FileImageRepository fileImageRepository;

    public Post getById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) throw new IllegalArgumentException("존재하지 않는 게시글입니다.");
        return postOptional.get();
    }

    @Transactional
    public PostDTO upload(PostCreateDTO postDTO, List<MultipartFile> files, String email) {
        Long categoryId = postDTO.getCategoryId();
        User user = userService.getByEmail(email);
        Category category = categoryService.getById(categoryId);
        String postContent = postDTO.getPostContent();
        Post post = postRepository.save(Post.builder()
                .user(user)
                .category(category)
                .postContent(postContent)
                .build()
        );

        List<PostFileDTO> postFileDTOS = new ArrayList<>();
        for (MultipartFile file : files) {
            String uploadUrl = s3Service.upload(file, "posts");
            log.info("uploadUrl: {}", uploadUrl);
            PostFileDTO postFileDTO = new PostFileDTO(fileImageRepository.save(
                    PostFile.builder()
                            .post(post)
                            .fileName(uploadUrl)
                            .build()
            ));
            postFileDTOS.add(postFileDTO);
        }

        return new PostDTO(post, postFileDTOS);
    }

    @Transactional
    public void update(Long postId, PostUpdateDTO postDTO, List<MultipartFile> files) {
        String postComment = postDTO.getPostContent();
        Category category = null;
        Post post = postRepository.findById(postId)
                .orElseThrow(
                        () -> new IllegalArgumentException("해당하는 게시물을 찾을 수 없습니다.")
                );

        Long categoryId = postDTO.getCategoryId();
        if (categoryId != null) {
            category = categoryService.getById(categoryId);
        }

//        List<PostFileDTO> postFileDTOs = new ArrayList<>();

        // 삭제할 기존 파일 조회
        List<Long> deleteImageIds = postDTO.getDeleteImageIds();
        if (deleteImageIds != null) {
            // postId에 해당하는 Image들을 조회
            List<PostFile> postFiles = fileImageRepository.findAllByPost_PostIdAndImageIdIn(postId, deleteImageIds);
            postFiles.forEach(postFile -> {
                // s3 파일 삭제
                s3Service.delete(postFile.getFileName());
                // db에서 삭제
                fileImageRepository.deleteById(postFile.getImageId());
            });
        }
        
//        // 삭제하지 않은 기존 파일 리스트 조회
//        // 왜? 반환해줘야 하니까
//        fileImageRepository.findAllByPost_PostId(postId).forEach(postFile -> {
//            postFileDTOs.add(new PostFileDTO(postFile));
//        });
        
        // 프론트에서 넘어온 새로운 파일 등록
        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                // 신규 파일 업로드
                String uploadUrl = s3Service.upload(file, "posts");
                log.info("uploadUrl: {}", uploadUrl);
                // db 저장
                fileImageRepository.save(
                        PostFile.builder()
                                .post(post)
                                .fileName(uploadUrl)
                                .build()
                );
//                PostFileDTO postFileDTO = new PostFileDTO(저장한거 넣기);
//                postFileDTOs.add(postFileDTO);
            }
        }

        post.update(
                postComment,
                category
        );
        postRepository.save(post);
//        return new PostDTO(post, postFileDTOs);
    }

    public List<Post> getAllPosts() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public boolean deleteById(Long postId) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                postRepository.delete(post);
                return true;
            } else {
                System.out.println("해당하는 게시물을 찾을 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public PostDTO getPostById(Long postId) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            // Retrieve the list of PostFileDTO objects associated with the post
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            // Create and return the PostDTO object
            return new PostDTO(post, postFileDTOS);
        } else {
            return null;
        }
    }

    private List<PostFileDTO> getFileImageDTOsForPost(Post post) {
        // Retrieve the list of PostFile objects associated with the post
        List<PostFile> postFiles = post.getPostFiles();
        // Convert the list of PostFile objects to PostFileDTO objects
        return postFiles.stream()
                .map(PostFileDTO::new)
                .collect(Collectors.toList());
    }
}
