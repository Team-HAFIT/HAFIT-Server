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

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class CommentLikeService {
    private final CommentLikeRepository commentLikeRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public boolean insertCommentLike(Long commentId, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

            CommentLike commentLike = CommentLike.builder()
                    .comment(comment)
                    .user(user)
                    .build();

            commentLikeRepository.save(commentLike);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean deleteCommentLike(Long commentId, String userEmail) {
        try {
            User user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + userEmail));
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found with id: " + commentId));

            CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                    .orElseThrow(() -> new NotFoundException("Could not find CommentLike"));

            commentLikeRepository.delete(commentLike);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
