package com.feedback.hafit.domain.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UserChangePasswordDTO {
    private String email;
    private String oldPassword;
    private String newPassword;

    @Builder
    public UserChangePasswordDTO(String email, String oldPassword, String newPassword) {
        this.email = email;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

}
