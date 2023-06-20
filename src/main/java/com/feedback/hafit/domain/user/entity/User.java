package com.feedback.hafit.domain.user.entity;

import com.feedback.hafit.domain.BaseEntity;
import com.feedback.hafit.domain.category.entity.Category;
import com.feedback.hafit.domain.comment.entity.Comment;
import com.feedback.hafit.domain.commentLike.entity.CommentLike;
import com.feedback.hafit.domain.goal.entity.Goal;
import com.feedback.hafit.domain.post.entity.Post;
import com.feedback.hafit.domain.postLike.entity.PostLike;
import com.feedback.hafit.domain.user.dto.request.UserLoginDTO;
import com.feedback.hafit.global.enumerate.Role;
import com.feedback.hafit.global.enumerate.SocialType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {

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

    @Builder
    public User(
            Long userId,
            String email,
            String password,
            String name,
            String carrier,
            String phone,
            Role role,
            SocialType socialType,
            String socialId,
            String imageUrl
    ) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.carrier = carrier;
        this.phone = phone;
        this.name = name;
        this.role = Role.USER;
        this.socialType = socialType;
        this.socialId = socialId;
        this.imageUrl = imageUrl;
    }

    public UserLoginDTO toLoginDTO() {
        return UserLoginDTO.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setPayment(Character payment) {
        this.payment = payment;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setSocialType(SocialType socialType) {
        this.socialType = socialType;
    }

    public void setSocialId(String socialId) {
        this.socialId = socialId;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void setPostLikes(List<PostLike> postLikes) {
        PostLikes = postLikes;
    }

    public void setCommentLikes(List<CommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(role.getValue()));
        return roles;
    }

    // Methods required by UserDetails interface

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Modify as needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Modify as needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Modify as needed
    }

}
