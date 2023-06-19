package com.feedback.hafit.domain.post.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.comment.service.CommentService;
import com.feedback.hafit.domain.post.dto.request.PostCreateDTO;
import com.feedback.hafit.domain.post.dto.request.PostUpdateDTO;
import com.feedback.hafit.domain.post.dto.response.PostFileDTO;
import com.feedback.hafit.domain.post.dto.response.PostForUserDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
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
    public void createPost(PostCreateDTO postDTO, List<MultipartFile> files, String email) {
        Long categoryId = postDTO.getCategoryId();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with category: " + categoryId));
        String post_content = postDTO.getPost_content();
        Post post = postRepository.save(Post.builder()
                .user(user)
                .category(category)
                .post_content(post_content)
                .build()
        );

        List<PostFileDTO> postFileDTOS = new ArrayList<>();
        for (MultipartFile file : files) {
            String uploadUrl = s3Service.upload(file, "posts");
            log.info("uploadUrl: {}", uploadUrl);
            PostFileDTO postFileDTO = new PostFileDTO(fileImageRepository.save(
                    PostFile.builder()
                            .post(post)
                            .file_name(uploadUrl)
                            .build()
            ));
            postFileDTOS.add(postFileDTO);
        }
    }

    @Transactional
    public void updatePost(Long postId, PostUpdateDTO postDTO, List<MultipartFile> files) {
        String postComment = postDTO.getPost_content();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with postId: " + postId));
        Long categoryId = postDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new EntityNotFoundException("Category not found with category: " + categoryId));

        List<Long> deleteImageIds = postDTO.getDeleteImageIds();
        if (deleteImageIds != null) {
            List<PostFile> postFiles = fileImageRepository.findAllByPost_PostIdAndImageIdIn(postId, deleteImageIds);
            postFiles.forEach(postFile -> {
                s3Service.delete(postFile.getFile_name());
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
                                .file_name(uploadUrl)
                                .build()
                );
            }
        }

        post.update(postComment, category);
        postRepository.save(post);
    }

    public List<PostWithLikesDTO> getAllPosts(Long lastPostId, int size, String email) {
        PageRequest pageRequest = PageRequest.of(0, size);
        Page<Post> pagePosts = postRepository.findByPostIdLessThanOrderByPostIdDesc(lastPostId, pageRequest);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        List<PostWithLikesDTO> postWithLikesDTOs = new ArrayList<>();
        for (Post post : pagePosts) {
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

    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with postId: " + postId));
        postRepository.delete(post);
    }

    // 게시글 1개 조회 좋아요 기능 추가
    public PostWithCommentsDTO getPostById(Long postId, String email) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found with postId: " + postId));
        List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
        boolean likedByUser = checkIfPostLikedByUser(post, user);
        Long commentCount = commentRepository.countByPost(post);
        Long totalLikes = postLikeRepository.countLikesByPost(post);
        List<CommentWithLikesDTO> commentDTOs = commentService.getCommentsByPostId(postId, email);

        for (CommentWithLikesDTO commentDTO : commentDTOs) {
            LocalDateTime commentTime = commentDTO.getModifiedAt();
            String timeAgo = getTimeAgo(commentTime);
            commentDTO.setUser_lastCommentTime(timeAgo);
        }

        return new PostWithCommentsDTO(post, postFileDTOS, likedByUser, totalLikes, commentCount, commentDTOs);
    }

    public List<PostFileDTO> getFileImageDTOsForPost(Post post) {
        List<PostFile> postFiles = post.getPostFiles();
        return postFiles.stream()
                .map(PostFileDTO::new)
                .collect(Collectors.toList());
    }

    private String getTimeAgo(LocalDateTime commentTime) {
        Duration duration = Duration.between(commentTime, LocalDateTime.now());

        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();
        long months = days / 30;
        long years = days / 365;

        if (minutes < 60) {
            return minutes + "분 전";
        } else if (hours < 24) {
            return hours + "시간 전";
        } else if (days < 30) {
            return days + "일 전";
        } else if (months < 12) {
            return months + "달 전";
        } else {
            return years + "년 전";
        }
    }

    // 내가 작성한 게시글
    public Map<String, Object> getMyPosts(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Could not find user with email: " + email));

        List<Post> myPosts = postRepository.findByUser(user);
        List<PostForUserDTO> postedPosts = new ArrayList<>();

        for (Post post : myPosts) {
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            PostForUserDTO postDTO = new PostForUserDTO(post, postFileDTOS);
            postedPosts.add(postDTO);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", postedPosts.size());
        result.put("posts", postedPosts);

        return result;
    }

    // 내가 좋아요한 게시글
    public Map<String, Object> getLikedPostsByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id " + email));
        List<PostLike> postLikes = postLikeRepository.findByUser(user);
        List<PostForUserDTO> likedPosts = new ArrayList<>();

        for (PostLike postLike : postLikes) {
            Post post = postLike.getPost();
            List<PostFileDTO> postFileDTOS = getFileImageDTOsForPost(post);
            PostForUserDTO postDTO = new PostForUserDTO(post, postFileDTOS);
            likedPosts.add(postDTO);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("count", likedPosts.size());
        result.put("posts", likedPosts);

        return result;
    }
}