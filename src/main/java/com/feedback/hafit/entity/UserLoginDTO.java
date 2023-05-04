package com.feedback.hafit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginDTO {
    private String email;
    private String password;

    public User toEntity() {
        com.feedback.hafit.entity.User user = com.feedback.hafit.entity.User.builder()
                .email(email)
                .password(password)
                .role(com.feedback.hafit.entity.Role.USER)
                .build();
        return user;
    }

    @Builder
    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
