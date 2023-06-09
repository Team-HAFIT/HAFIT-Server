package com.feedback.hafit.domain.comment.service;

import com.feedback.hafit.domain.comment.dto.request.CommentCreateDTO;
import com.feedback.hafit.domain.comment.dto.response.CommentDTO;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.comment.repository.CommentRepository;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.service.PostService;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
@Slf4j
public class CommentService {
    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CommentRepository commentRepository;

    @Transactional
    public CommentDTO write(CommentCreateDTO commentCreateDTO, String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email : " + email));
        Long postId = commentCreateDTO.getPostId();
        Long commentId = commentCreateDTO.getCommentId();
        String content = commentCreateDTO.getContent();
        Post post = postService.getById(postId);

        Comment comment = commentRepository.save(Comment.builder()
                .commentId(commentId)
                .content(content)
                .user(user)
                .post(post)
                .build()
        );
        return new CommentDTO(comment);

//    public CommentDTO update(CommentUpdateDTO commentUpdateDTO) {
//        Long commentId = commentUpdateDTO.getCommentId();
//        String content = commentUpdateDTO.getContent();
//
//        Comment comment = commentRepository.findById(commentId)
//                .orElseThrow(() -> new IllegalArgumentException("Comment not found with ID: " + commentId));
//
//        comment.setContent(content);
//        commentRepository.save(comment);
//
//        return CommentDTO.builder()
//                .commentId(comment.getCommentId())
//                .content(comment.getContent())
//                .name(comment.getUser().getName())
//                .userId(comment.getUser().getUserId())
//                .postId(comment.getPost().getPostId())
//                .build();
//    }
    }
}
