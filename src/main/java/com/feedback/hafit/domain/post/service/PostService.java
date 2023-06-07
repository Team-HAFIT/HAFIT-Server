package com.feedback.hafit.domain.post.service;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.category.service.CategoryService;
import com.feedback.hafit.domain.post.dto.reqeust.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.reqeust.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.FileImageDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.entity.FileImage;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.repository.FileImageRepository;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final UserService userService;

    private final PostRepository postRepository;

    private final CategoryRepository categoryRepository;

    private final CategoryService categoryService;

    private final S3Service s3Service;

    private final FileImageRepository fileImageRepository;

    @Transactional
    public PostDTO upload(PostCreateDTO postDTO, List<MultipartFile> files, String email) {
        Long categoryId = postDTO.getCategoryId();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        Category category = categoryService.getById(categoryId);
        String postContent = postDTO.getPostContent();
        Post post = postRepository.save(Post.builder()
                .user(user)
                .category(category)
                .postContent(postContent)
                .build()
        );

        List<FileImageDTO> fileImageDTOs = new ArrayList<>();
        for (MultipartFile file : files) {
            String uploadUrl = s3Service.upload(file, "posts");
            log.info("uploadUrl: {}", uploadUrl);
            FileImageDTO fileImageDTO = new FileImageDTO(fileImageRepository.save(
                    FileImage.builder()
                            .post(post)
                            .fileName(uploadUrl)
                            .build()
            ));
            fileImageDTOs.add(fileImageDTO);
        }

        return new PostDTO(post, fileImageDTOs);
    }

    public boolean update(Long postId, PostUpdateDTO postDTO, List<MultipartFile> files) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setPostContent(postDTO.getPostContent());

                Long categoryId = postDTO.getCategoryId();
                Category category = categoryService.getById(categoryId);
                post.setCategory(category);

                List<FileImageDTO> fileImageDTOs = new ArrayList<>();
                for (MultipartFile file : files) {
                    String uploadUrl = s3Service.upload(file, "posts");
                    log.info("uploadUrl: {}", uploadUrl);
                    FileImageDTO fileImageDTO = new FileImageDTO(fileImageRepository.save(
                            FileImage.builder()
                                    .post(post)
                                    .fileName(uploadUrl)
                                    .build()
                    ));
                    fileImageDTOs.add(fileImageDTO);
                }

                postRepository.save(post);
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
}
