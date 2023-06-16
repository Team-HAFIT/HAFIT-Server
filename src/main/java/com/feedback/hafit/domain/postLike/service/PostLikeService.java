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

@Service
@RequiredArgsConstructor
public class PostLikeService {
    private final PostLikeRepository postLikeRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Transactional
    public void insert(Long postId, String userEmail) throws Exception {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + userEmail));

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + postId));

        // 이미 좋아요되어있으면 에러 반환
        if (postLikeRepository.findByUserAndPost(user, post).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        PostLike postLike = PostLike.builder()
                .post(post)
                .user(user)
                .build();

        postLikeRepository.save(postLike);
    }

    @Transactional
    public boolean delete(Long postId, String userEmail) {
        try {
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new NotFoundException("Could not found board id : " + postId));
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new NotFoundException("Could not found member id : " + userEmail));

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