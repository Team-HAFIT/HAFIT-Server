package com.feedback.hafit.domain.user.dto;

import com.feedback.hafit.domain.user.enumerate.Role;
import com.feedback.hafit.domain.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long user_id; //sequence, auto_increment

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

    private String user_img;

    public User toEntity() {
        User user = User.builder()
                .email(email)
                .password(password)
                .carrier(carrier)
                .phone(phone)
                .sex(sex)
                .name(name)
                .birthday(birthday)
                .height(height)
                .weight(weight)
                .user_img(user_img)
                .role(Role.USER)
                .build();
        return user;
    }

    @Builder
    public UserDTO(Long user_id, String email, String password, String carrier, String phone, String sex, String name, String user_img, int height, int weight, LocalDate birthday, Role role) {
        this.user_id = user_id;
        this.email = email;
        this.password = password;
        this.carrier = carrier;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.user_img = user_img;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday;
        this.role = role;
    }

}
