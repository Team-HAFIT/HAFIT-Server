package com.feedback.hafit.service;

import com.feedback.hafit.domain.Category;
import com.feedback.hafit.domain.Post;
import com.feedback.hafit.domain.User;
import com.feedback.hafit.dto.PostDTO;
import com.feedback.hafit.repository.CategoryRepository;
import com.feedback.hafit.repository.PostRepository;
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
            String postContent = postDTO.getPost_content();
            Post post = new Post();
            post.setUser(user);
            post.setCategory(category);
            post.setPost_content(postContent);
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean update(PostDTO postDTO) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postDTO.getPost_id());
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setPost_content(postDTO.getPost_content());
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
            Optional<Post> optionalPost = postRepository.findById(postDTO.getPost_id());
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
