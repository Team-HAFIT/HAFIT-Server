package com.feedback.hafit.domain.post.dto.response;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.post.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO extends BaseEntity {
    private Long postId;
    private Long categoryId;
    private String userName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();

    public PostDTO(Post post, List<PostFileDTO> files) {
        this.postId = post.getPostId();
        this.categoryId = post.getCategory().getCategoryId();
        this.userName = post.getUser().getName();
        this.postContent = post.getPostContent();
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}