package com.feedback.hafit.domain.user.dto.request;

import com.feedback.hafit.global.enumerate.Role;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

}
