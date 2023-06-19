package com.feedback.hafit.domain.user.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserChangePasswordDTO {
    private String email;
    private String oldPassword;
    private String newPassword;

}
