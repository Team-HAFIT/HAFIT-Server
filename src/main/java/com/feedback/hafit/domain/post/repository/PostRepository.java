package com.feedback.hafit.domain.post.repository;

import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findByCategory(Category category);
}
