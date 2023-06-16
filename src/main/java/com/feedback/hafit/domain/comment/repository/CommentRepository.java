package com.feedback.hafit.domain.comment.repository;

import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    Long countByPost(Post post);

    List<Comment> findByUser(User user);

}
