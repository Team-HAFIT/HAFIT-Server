package com.feedback.hafit.domain.user.dto;

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

    @Builder
    public UserLoginDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
