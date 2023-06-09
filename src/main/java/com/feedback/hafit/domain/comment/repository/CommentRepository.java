package com.feedback.hafit.domain.comment.repository;

import com.feedback.hafit.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    //    List<Comment> findByPostId(Long postId); // 특정 게시글의 댓글 및 대댓글 조회

}
