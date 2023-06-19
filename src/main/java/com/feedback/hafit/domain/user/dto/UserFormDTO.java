package com.feedback.hafit.domain.user.dto;

import com.feedback.hafit.global.enumerate.Role;
import com.feedback.hafit.domain.user.entity.User;
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
    private Long userId;
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

    @Builder
    public UserFormDTO(String email, String password, String name, String carrier, String phone, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.carrier = carrier;
        this.phone = phone;
        this.role = role;
    }
}
