package com.feedback.hafit.domain.user.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.commentlike.CommentLike;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.postlike.PostLike;
import com.feedback.hafit.domain.user.dto.UserLoginDTO;
import com.feedback.hafit.domain.user.enumerate.Role;
import com.feedback.hafit.domain.user.enumerate.SocialType;
import com.feedback.hafit.domain.user.enumerate.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
    private String name; // 이름

    @Column
    private int weight;

    @Column
    private int height;

    @Column
    private String imageUrl; // 프로필 이미지

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

    public UserLoginDTO toLoginDTO() {
        return UserLoginDTO.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getValue()));
       return roles;
    }

}
