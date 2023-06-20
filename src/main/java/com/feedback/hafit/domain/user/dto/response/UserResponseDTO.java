package com.feedback.hafit.domain.user.dto.response;

import com.feedback.hafit.domain.user.entity.User;
import com.feedback.hafit.global.enumerate.Role;
import com.feedback.hafit.global.enumerate.SocialType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

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
    private LocalDateTime modifiedAt;
    private LocalDateTime createdAt;
    private SocialType socialType; // KAKAO, NAVER, GOOGLE
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    public UserResponseDTO(User user) {
        this.userId = user.getUserId();
        this.email = user.getEmail();
        this.carrier = user.getCarrier();
        this.phone = user.getPhone();
        this.sex = user.getSex();
        this.name = user.getName();
        this.role = user.getRole();
        this.birthday = user.getBirthday();
        this.height = user.getHeight();
        this.weight = user.getWeight();
        this.imageUrl = user.getImageUrl();
        this.socialId = user.getSocialId();
        this.socialType = user.getSocialType();
        this.modifiedAt = user.getModifiedAt();
        this.createdAt = user.getCreatedAt();
    }
}
