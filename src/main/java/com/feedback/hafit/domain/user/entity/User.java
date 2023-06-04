package com.feedback.hafit.domain.user.entity;

import com.feedback.hafit.domain.*;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.commentlike.CommentLike;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.postlike.PostLike;
import com.feedback.hafit.domain.user.enumerate.Role;
import com.feedback.hafit.domain.user.enumerate.SocialType;
import com.feedback.hafit.domain.user.enumerate.UserStatus;
import com.feedback.hafit.domain.user.dto.UserLoginDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId; //sequence, auto_increment

    @Column(unique = true)
    private String email; //아이디

    @Column(length = 100)
    private String password;

    @Column
    private String carrier;

    @Column(unique = true)
    private String phone; // 전화

    @Column(length = 10)
    private String sex;

    @Column
    private String nickname; // 닉네임

    @Column
    private String name; // 이름

    @Column
    private int weight;

    @Column
    private int height;

    @Column
    private String imageUrl; // 프로필 이미지

    @Column
    private Character easy_login;

    @Column
    private String token;

    @Column
    private Character payment;

    @Column
    private LocalDate birthday;

    @Enumerated(EnumType.STRING)
    private UserStatus user_status;

    @Enumerated(EnumType.STRING)
    private Role role; // 권한

    @Enumerated(EnumType.STRING)
    private SocialType socialType; // KAKAO, NAVER, GOOGLE

    @Column
    private String socialId; // 로그인한 소셜 타입의 식별자 값 (일반 로그인인 경우 null)

    @Column
    private String refreshToken; // 리프레시 토큰

    // 유저 권한 설정 메소드
    public void authorizeUser() {
        this.role = Role.USER;
    }

    public void updateRefreshToken(String updateRefreshToken) {
        this.refreshToken = updateRefreshToken;
    }

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PostLike> PostLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommentLike> commentLikes = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Category> categories = new ArrayList<>();

//    @Builder
//    public User(String email, String password, String carrier, String phone, String sex, String name, int weight, int height,
//                      String user_img, Character easy_login, String token, Character payment, LocalDate birthday, UserStatus user_status, Role role) {
//        this.email = email;
//        this.password = password;
//        this.carrier = carrier;
//        this.phone = phone;
//        this.sex = sex;
//        this.name = name;
//        this.weight = weight;
//        this.height = height;
//        this.user_img = user_img;
//        this.easy_login = easy_login;
//        this.token = token;
//        this.payment = payment;
//        this.birthday = birthday;
//        this.user_status = user_status;
//        this.role = role;
//    }

    public UserLoginDTO toLoginDTO() {
        return UserLoginDTO.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

//    // override
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        ExerciseSet<GrantedAuthority> roles = new HashSet<>();
//        roles.add(new SimpleGrantedAuthority(role.getValue()));
//        return roles;
//    }
//
//    @Override
//    public String getUsername() {
//        return this.email;
//    }
//
//    @Override
//    public String getPassword(){
//        return this.password;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    // 사용자의 id를 반환 (unique한 값)
//    @Override
//    public String getUsername() {
//        return email;
//    }
//
//    // 사용자의 password를 반환
//    @Override
//    public String getPassword() {
//        return password;
//    }
//
//    // 계정 만료 여부 반환
//    @Override
//    public boolean isAccountNonExpired() {
//        // 만료되었는지 확인하는 로직
//        return true; // true -> 만료되지 않았음
//    }
//
//    // 계정 잠금 여부 반환
//    @Override
//    public boolean isAccountNonLocked() {
//        // 계정 잠금되었는지 확인하는 로직
//        return true; // true -> 잠금되지 않았음
//    }
//
//    // 패스워드의 만료 여부 반환
//    @Override
//    public boolean isCredentialsNonExpired() {
//        // 패스워드가 만료되었는지 확인하는 로직
//        return true; // true -> 만료되지 않았음
//    }
//
//    // 계정 사용 가능 여부 반환
//    @Override
//    public boolean isEnabled() {
//        // 계정이 사용 가능한지 확인하는 로직
//        return true; // true -> 사용 가능
//    }
}
