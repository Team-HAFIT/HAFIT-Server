package com.feedback.hafit.domain.post.repository;

import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.post.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileImageRepository extends JpaRepository<PostFile, Long> {
    List<PostFile> findAllByPost_PostIdAndImageIdIn(Long postId, List<Long> ids);
    List<PostFile> findAllBypost(Post postId);
}
