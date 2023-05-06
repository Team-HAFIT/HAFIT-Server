package com.feedback.hafit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostFormDTO {
    private String post_content;
    private String post_img;

    public Post toEntity() {
        Post post = Post.builder()
                .post_content(post_content)
                .post_img(post_img)
                .build();
        return post;
    }

    @Builder
    public PostFormDTO(String post_content, String post_img) {
        this.post_content = post_content;
        this.post_img = post_img;
    }
}