package com.feedback.hafit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.feedback.hafit.repository.UserRepository;
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
    private String post_content;
    private String post_file;

    @JsonIgnore
    private Long user_id;

    public Post toEntity() {
        User user = userRepository.findById(user_id).orElse(null);
        Post post = Post.builder()
                .post_content(post_content)
                .post_file(post_file)
                .user(user)
                .build();
        return post;
    }
    @Builder
    public PostDTO(String post_content, String post_file, Long user_id) {
        this.post_content = post_content;
        this.post_file = post_file;
        this.user_id = user_id;
    }
}