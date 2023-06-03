package com.feedback.hafit.domain.user.dto;

import com.feedback.hafit.domain.user.enumerate.Role;
import com.feedback.hafit.domain.user.entity.User;
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
        User user = User.builder()
                .email(email)
                .password(password)
                .role(Role.USER)
                .build();
        return user;
    }

    @Builder
    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
