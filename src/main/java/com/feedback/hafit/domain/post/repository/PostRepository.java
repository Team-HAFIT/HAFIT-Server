package com.feedback.hafit.domain.post.repository;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category);

    @Query("SELECT p, COUNT(c) AS commentCount FROM Post p LEFT JOIN p.comments c WHERE p.id = :postId GROUP BY p")
    Optional<Object[]> findPostWithCommentCountById(@Param("post") Post post);

    List<Post> findByUser(User user);
}
