package com.feedback.hafit.domain.post.service;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.service.UserService;
import com.feedback.hafit.domain.post.dto.PostDTO;
import com.feedback.hafit.domain.category.repository.CategoryRepository;
import com.feedback.hafit.domain.post.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public boolean upload(PostDTO postDTO, Long userId, Long categoryId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return false;
        }
        Category category = categoryRepository.findById(categoryId).orElse(null);
        if (category == null) {
            return false;
        }
        try {
            String postContent = postDTO.getPostContent();
            Post post = new Post();
            post.setUser(user);
            post.setCategory(category);
            post.setPostContent(postContent);
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(PostDTO postDTO) {
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

    public boolean delete(PostDTO postDTO) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postDTO.getPostId());
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

    public List<Post> getAllPosts() {
        try {
            return postRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}
