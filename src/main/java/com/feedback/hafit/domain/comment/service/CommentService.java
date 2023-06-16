package com.feedback.hafit.domain.comment.service;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentWithLikesDTO;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.commentLike.entity.CommentLike;
import com.feedback.hafit.domain.commentLike.repository.CommentLikeRepository;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.repository.PostRepository;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentLikeRepository commentLikeRepository;

    @Transactional
    public boolean write(Long postId, CommentCreateDTO commentCreateDTO, String email) {
        try {
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
            String content = commentCreateDTO.getCommentContent();
            Post post = postRepository.findById(postId)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with postId: " + postId));

            Comment comment = commentRepository.save(Comment.builder()
                    .content(content)
                    .user(user)
                    .post(post)
                    .build()
            );

            return comment != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<CommentWithLikesDTO> getAllComments(String email) {
        List<Comment> comments = commentRepository.findAll();
        List<CommentWithLikesDTO> commentsWithLikesDTOs = new ArrayList<>();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        for (Comment comment : comments) {
            Long totalLikes = commentLikeRepository.countLikesByComment(comment);
            boolean likedByUser = checkIfCommentLikedByUser(comment, user);

            CommentWithLikesDTO commentWithLikesDTO = new CommentWithLikesDTO(comment, likedByUser, totalLikes);
            commentsWithLikesDTOs.add(commentWithLikesDTO);
        }

        return commentsWithLikesDTOs;
    }


    private boolean checkIfCommentLikedByUser(Comment comment, User user) {
        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByUserAndComment(user, comment);
        return optionalCommentLike.isPresent();
    }

    public boolean deleteById(Long commentId) {
        try {
            Optional<Comment> optionalComment = commentRepository.findById(commentId);
            if (optionalComment.isPresent()) {
                Comment comment = optionalComment.get();
                commentRepository.delete(comment);
                return true;
            } else {
                System.out.println("해당하는 댓글을 찾을 수 없습니다.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Transactional
    public boolean update(Long commentId, String newContent) {
        try {
            Comment comment = commentRepository.findById(commentId)
                    .orElseThrow(() -> new EntityNotFoundException("Comment not found with commentId: " + commentId));

            comment.setContent(newContent);
            Comment updatedComment = commentRepository.save(comment);

            return updatedComment != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<CommentWithLikesDTO> getCommentsByPostId(Long postId, String email) {
        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

            List<Comment> comments = commentRepository.findByPost(post); // 게시글 ID에 해당하는 댓글 조회
            List<CommentWithLikesDTO> commentDTOs = new ArrayList<>();

            for (Comment comment : comments) {
                boolean likedByUser = checkIfCommentLikedByUser(comment, user);
                Long totalLikes = commentLikeRepository.countLikesByComment(comment);

                CommentWithLikesDTO commentDTO = new CommentWithLikesDTO(comment, likedByUser, totalLikes);
                commentDTOs.add(commentDTO);
            }

            return commentDTOs;
        } else {
            throw new EntityNotFoundException("Post not found with ID: " + postId);
        }
    }

}
