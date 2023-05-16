package com.feedback.hafit.service;

import com.feedback.hafit.entity.Post;
import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public boolean upload(PostFormDTO postFormDTO) {
        try {
            postRepository.save(postFormDTO.toEntity());
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
    }
    return false;
}




}
