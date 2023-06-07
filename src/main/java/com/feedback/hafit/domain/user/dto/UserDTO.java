package com.feedback.hafit.domain.user.dto;

import com.feedback.hafit.domain.user.enumerate.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long userId; //sequence, auto_increment

    private String email; //아이디

    private String password;

    private String carrier;
    private String phone; // 전화

    private String sex;

    private String name; // 이름

    private Role role; // 권한

    private LocalDate birthday;

    private int height;

    private int weight;

    private String imageUrl;

    @Builder
    public UserDTO(Long userId, String email, String password, String carrier, String phone, String sex, String name, String imageUrl, int height, int weight, LocalDate birthday, Role role) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.carrier = carrier;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.imageUrl = imageUrl;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.role = role;
    }

}
