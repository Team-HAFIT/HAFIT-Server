package com.feedback.hafit.domain.user.dto.request;

import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.global.enumerate.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserFormDTO {

    @NotNull
    @Id
    private Long user_id;
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private String carrier;
    @NotNull
    private String phone;

    private Role role;

    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .carrier(carrier)
                .phone(phone)
                .name(name)
                .role(Role.USER)
                .build();
        return user;
    }

}
