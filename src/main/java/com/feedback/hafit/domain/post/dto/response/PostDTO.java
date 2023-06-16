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
    private Long postId;
    private String category_name;
    private String user_name;
    private String post_content;
    private List<PostFileDTO> files = Collections.emptyList();
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;

    public PostDTO(Post post, List<PostFileDTO> files) {
        this.postId = post.getPostId();
        this.category_name = post.getCategory().getCategory_name();
        this.user_name = post.getUser().getName();
        this.post_content = post.getPost_content();
        this.files = files;
        this.modifiedAt = post.getModifiedAt();
        this.createdAt = post.getCreatedAt();
    }

}