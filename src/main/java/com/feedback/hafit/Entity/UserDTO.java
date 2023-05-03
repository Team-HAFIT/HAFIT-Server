package com.feedback.hafit.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
    private Long num; //sequence, auto_increment

    private String email; //아이디

    private String password;

    private String birth; //생일

    private String phone; // 전화

    private String sex;

    private String name; // 이름

    private String nickname; //닉네임

    private Role role; // 권한

    private LocalDateTime created_at;


    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .birth(birth)
                .phone(phone)
                .sex(sex)
                .name(name)
                .nickname(nickname)
                .role(Role.USER)
                .created_at(created_at)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long num, String email, String password, String birth, String phone, String sex, String name, String nickname, Role role, LocalDateTime created_at) {
        this.num = num;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.nickname = nickname;
        this.role = role;
        this.created_at = created_at;
    }
}
