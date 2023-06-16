package com.feedback.hafit.domain.postLike.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.postLike.repository.PostLikeRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public boolean insertPostLike(Long postId, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new EntityNotFoundException("User not find with email: " + userEmail));

            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new EntityNotFoundException("Post not find with Id: " + postId));

            PostLike postLike = PostLike.builder()
                    .post(post)
                    .user(user)
                    .build();

            postLikeRepository.save(postLike);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Transactional
    public boolean deletePostLike(Long postId, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new EntityNotFoundException("User not find with email: " + userEmail));

            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new EntityNotFoundException("Post not find with Id: " + postId));

            PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                    .orElseThrow(() -> new NotFoundException("Could not found postLike id"));

            postLikeRepository.delete(postLike);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
