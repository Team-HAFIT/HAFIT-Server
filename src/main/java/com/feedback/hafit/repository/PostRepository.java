package com.feedback.hafit.repository;

import com.feedback.hafit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Post findByPost_id(Long post_id);

    Optional<Post> findPostByPost_id(Long post_id);
}
