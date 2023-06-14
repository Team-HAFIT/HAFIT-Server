package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWithLikesDTO {
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private Long postId;
    private Long categoryId;
    private String userName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();
    private Long totalLikes;

    public PostWithLikesDTO(Post post, List<PostFileDTO> files, Long totalLikes) {
        this.postId = post.getPostId();
        this.categoryId = post.getCategory().getCategoryId();
        this.userName = post.getUser().getName();
        this.postContent = post.getPostContent();
        this.totalLikes = totalLikes;
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}