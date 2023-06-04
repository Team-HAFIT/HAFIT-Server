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
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.global.s3.service.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
public class PostService {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryService categoryService;

    @Autowired
    S3Service s3Service;

    @Autowired
    FileImageRepository fileImageRepository;

    @Transactional
    public PostDTO upload(PostCreateDTO postDTO) {
        Long userId = postDTO.getUserId(); // Long userId
        Long categoryId = postDTO.getCategoryId(); // Long categoryId
        User user = userService.getById(userId);
        Category category = categoryService.getById(categoryId);
        String postContent = postDTO.getPostContent();
        Post post = postRepository.save(Post.builder()
                .user(user)
                .category(category)
                .postContent(postContent)
                .build()
        );

        List<FileImageDTO> files = postDTO.getFiles().stream().map(file -> {
            String uploadUrl = s3Service.upload(file, "posts");
            log.info("uploadUrl: {}", uploadUrl);
            return new FileImageDTO(fileImageRepository.save(
                    FileImage.builder()
                            .post(post)
                            .fileName(uploadUrl)
                            .build()
            ));
        }).toList();
        return new PostDTO(post, files);
    }


    public boolean update(PostUpdateDTO postDTO) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postDTO.getPostId());
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setPostContent(postDTO.getPostContent());
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
