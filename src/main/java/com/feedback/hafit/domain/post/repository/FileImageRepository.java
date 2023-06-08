package com.feedback.hafit.domain.post.repository;

import com.feedback.hafit.domain.post.entity.PostFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileImageRepository extends JpaRepository<PostFile, Long> {

}
