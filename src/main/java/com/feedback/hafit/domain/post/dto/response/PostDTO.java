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
public class PostDTO{
    private String categoryName;
    private String userName;
    private String postContent;
    private List<PostFileDTO> files = Collections.emptyList();
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostDTO(Post post, List<PostFileDTO> files) {
        this.categoryName = post.getCategory().getCategoryName();
        this.userName = post.getUser().getName();
        this.postContent = post.getPostContent();
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}