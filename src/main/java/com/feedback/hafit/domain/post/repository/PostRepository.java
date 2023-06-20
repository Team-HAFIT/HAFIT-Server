package com.feedback.hafit.domain.post.repository;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    // 무한 스크롤
    @Query(value = "SELECT p FROM Post p WHERE p.postId < ?1 ORDER BY p.postId DESC")
    Page<Post> findByPostIdLessThanOrderByPostIdDesc(Long lastPostId, PageRequest pageRequest);

    Page<Post> findByPostIdLessThanAndCategoryOrderByPostIdDesc(Long lastPostId, Category category, PageRequest pageRequest);
}
