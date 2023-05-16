package com.feedback.hafit.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
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

    @NotNull
    private String phone;

    @NotNull
    private UserStatus user_status;
    private Role role;

    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .phone(phone)
                .name(name)
                .user_status(UserStatus.ACTIVE)
                .role(Role.USER)
                .build();
        return user;
    }

    @Builder
    public UserFormDTO(String email, String password, String name, String phone, UserStatus user_status, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.user_status = user_status;
        this.role = role;
    }
}