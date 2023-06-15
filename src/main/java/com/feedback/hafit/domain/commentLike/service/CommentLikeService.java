package com.feedback.hafit.domain.commentLike.service;

import com.amazonaws.services.kms.model.NotFoundException;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.commentLike.entity.CommentLike;
import com.feedback.hafit.domain.commentLike.repository.CommentLikeRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public void insert(Long commentId, String userEmail) throws Exception {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + userEmail));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Could not found board id : " + commentId));

        // 이미 좋아요되어있으면 에러 반환
        if (commentLikeRepository.findByUserAndComment(user, comment).isPresent()){
            //TODO 409에러로 변경
            throw new Exception();
        }

        CommentLike commentLike = CommentLike.builder()
                .comment(comment)
                .user(user)
                .build();

        commentLikeRepository.save(commentLike);
    }

    @Transactional
    public void delete(Long commentId, String userEmail) {

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new NotFoundException("Could not found member id : " + userEmail));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException("Could not found comment id : " + commentId));

        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new NotFoundException("Could not found heart id"));

        commentLikeRepository.delete(commentLike);
    }
}
