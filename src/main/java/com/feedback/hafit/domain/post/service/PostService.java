package com.feedback.hafit.domain.post.service;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.comment.service.CommentService;
import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostDTO;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithCommentsDTO;
import com.feedback.hafit.domain.post.dto.response.PostWithLikesDTO;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.entity.PostFile;
import com.feedback.hafit.domain.post.repository.FileImageRepository;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.postLike.repository.PostLikeRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;
    private final S3Service s3Service;
    private final FileImageRepository fileImageRepository;
    private final PostLikeRepository postLikeRepository;
    private final CommentService commentService;
    private final CommentRepository commentRepository;

    @Transactional
    public PostDTO upload(PostCreateDTO postDTO, List<MultipartFile> files, String email) {
        Long categoryId = postDTO.getCategoryId();
        log.info(String.valueOf(categoryId));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with category: " + categoryId));
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
    public boolean update(Long postId, PostUpdateDTO postDTO, List<MultipartFile> files) {
        try {
            String postComment = postDTO.getPostContent();
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new IllegalArgumentException("해당하는 게시물을 찾을 수 없습니다."));

            Long categoryId = postDTO.getCategoryId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new EntityNotFoundException("Category not found with category: " + categoryId));

            List<Long> deleteImageIds = postDTO.getDeleteImageIds();
            if (deleteImageIds != null) {
                List<PostFile> postFiles = fileImageRepository.findAllByPost_PostIdAndImageIdIn(postId, deleteImageIds);
                postFiles.forEach(postFile -> {
                    s3Service.delete(postFile.getFileName());
                    fileImageRepository.deleteById(postFile.getImageId());
                });
            }

            if (files != null && !files.isEmpty()) {
                for (MultipartFile file : files) {
                    String uploadUrl = s3Service.upload(file, "posts");
                    log.info("uploadUrl: {}", uploadUrl);
                    fileImageRepository.save(
                            PostFile.builder()
                                    .post(post)
                                    .fileName(uploadUrl)
                                    .build()
                    );
                }
            }

            post.update(postComment, category);
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<PostWithLikesDTO> getAllPosts(String email) {
        List<Post> posts = postRepository.findAll();
        List<PostWithLikesDTO> postWithLikesDTOs = new ArrayList<>();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        for (Post post : posts) {
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            Long totalLikes = postLikeRepository.countLikesByPost(post);
            Long commentCount = commentRepository.countByPost(post);
            boolean likedByUser = checkIfPostLikedByUser(post, user);

            PostWithLikesDTO postWithLikesDTO = new PostWithLikesDTO(post, postFileDTOS, likedByUser, totalLikes, commentCount);
            postWithLikesDTOs.add(postWithLikesDTO);
        }

        return postWithLikesDTOs;
    }

    public boolean checkIfPostLikedByUser(Post post, User user) {
        Optional<PostLike> optionalPostLike = postLikeRepository.findByUserAndPost(user, post);
        return optionalPostLike.isPresent();
    }


    public boolean deleteById(Long postId) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postId);
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                postRepository.delete(post);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 게시글 1개 조회 좋아요 기능 추가
    public PostWithCommentsDTO getPostById(Long postId, String email) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
            boolean likedByUser = checkIfPostLikedByUser(post, user);

            Long commentCount = commentRepository.countByPost(post);
            Long totalLikes = postLikeRepository.countLikesByPost(post);
            List<CommentWithLikesDTO> commentDTOs = commentService.getCommentsByPostId(postId, email);

            PostWithCommentsDTO postWithCommentsDTO = new PostWithCommentsDTO(post, postFileDTOS, likedByUser, totalLikes, commentCount, commentDTOs);

            return postWithCommentsDTO;
        } else {
            return null;
        }
    }

    public List<PostFileDTO> getFileImageDTOsForPost(Post post) {
        List<PostFile> postFiles = post.getPostFiles();
        return postFiles.stream()
                .map(PostFileDTO::new)
                .collect(Collectors.toList());
    }
}
