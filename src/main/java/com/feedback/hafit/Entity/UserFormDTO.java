package com.feedback.hafit.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class UserFormDTO {
    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .phone(phone)
                .name(name)
                .build();
        return user;
    }

    @Builder
    public UserFormDTO(String email, String password, String name, String phone) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }
}
