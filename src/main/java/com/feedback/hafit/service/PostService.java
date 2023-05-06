/*
package com.feedback.hafit.service;

import com.feedback.hafit.entity.PostFormDTO;
import com.feedback.hafit.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public boolean write(PostFormDTO postFormDTO) {
        try {
            postRepository.save(postFormDTO.toEntity());
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}*/
