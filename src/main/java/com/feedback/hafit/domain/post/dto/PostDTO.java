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

    private Long post_id;
    private String post_content;
    @JsonIgnore
    private Long user_id;

    public Post toEntity() {
        User user = userRepository.findById(user_id).orElse(null);

        Post post = Post.builder()
                .post_content(post_content)
                .user(user)
                .build();
        return post;
    }

    @Builder
    public PostDTO(String post_content, Long user_id) {
        this.post_content = post_content;
        this.user_id = user_id;
    }
}