package com.feedback.hafit.service;

import com.feedback.hafit.entity.Post;
import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.entity.User;
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

    public boolean upload(PostFormDTO postFormDTO, Long userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return false;
        }
        try {
            String postContent = postFormDTO.getPost_content();
            String postFile = postFormDTO.getPost_file();
            Post post = new Post();
            post.setUser(user);
            post.setPost_content(postContent);
            post.setPost_file(postFile);
            postRepository.save(post);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(PostFormDTO postFormDTO) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postFormDTO.getPost_id());
            if (optionalPost.isPresent()) {
                Post post = optionalPost.get();
                post.setPost_content(postFormDTO.getPost_content());
                post.setPost_file(postFormDTO.getPost_file());
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

    public boolean delete(PostFormDTO postFormDTO) {
        try {
            Optional<Post> optionalPost = postRepository.findById(postFormDTO.getPost_id());
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
