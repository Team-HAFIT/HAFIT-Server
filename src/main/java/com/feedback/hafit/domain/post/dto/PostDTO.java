package com.feedback.hafit.domain.post.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.domain.user.repository.UserRepository;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
    @Autowired
    @JsonIgnore
    UserRepository userRepository;

    private Long postId;
    private String postContent;
    @JsonIgnore
    private Long userId;

    public Post toEntity() {
        User user = userRepository.findById(userId).orElse(null);

        Post post = Post.builder()
                .postContent(postContent)
                .user(user)
                .build();
        return post;
    }

    @Builder
    public PostDTO(String postContent, Long userId) {
        this.postContent = postContent;
        this.userId = userId;
    }
}