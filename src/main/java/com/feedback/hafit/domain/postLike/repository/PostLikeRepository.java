package com.feedback.hafit.domain.postLike.repository;

import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLike, Long> {
    Optional<PostLike> findByUserAndPost(User user, Post post);

    // 특정 게시물의 좋아요 수 합산
    @Query("SELECT COUNT(pl) FROM PostLike pl WHERE pl.post = :post")
    Long countLikesByPost(@Param("post") Post post);

    List<PostLike> findByUser(User user);
}
