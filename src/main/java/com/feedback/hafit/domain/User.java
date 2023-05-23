package com.feedback.hafit.domain;

import com.feedback.hafit.dto.UserLoginDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id; //sequence, auto_increment

    @Column(nullable = false, unique = true, length = 50)
    private String email; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column
    private String carrier;

    @Column(nullable = false, unique = true, length = 30)
    private String phone; // 전화

    @Column(length = 10)
    private String sex;

    @Column(nullable = false, length = 50)
    private String name; // 이름

    @Column
    private int weight;

    @Column
    private int height;

    @Column
    private String user_img;

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

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    @OneToMany(mappedBy = "user")
    private List<Goal> goals;

    // Category와의 관계 설정
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();

    @Builder
    public User(String email, String password, String carrier, String phone, String sex, String name, int weight, int height,
                      String user_img, Character easy_login, String token, Character payment, LocalDate birthday, UserStatus user_status, Role role) {
        this.email = email;
        this.password = password;
        this.carrier = carrier;
        this.phone = phone;
        this.sex = sex;
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.user_img = user_img;
        this.easy_login = easy_login;
        this.token = token;
        this.payment = payment;
        this.birthday = birthday;
        this.user_status = user_status;
        this.role = role;
    }

    public UserLoginDTO toLoginDTO() {
        return UserLoginDTO.builder()
                .email(this.email)
                .password(this.password)
                .build();
    }

//    // override
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set<GrantedAuthority> roles = new HashSet<>();
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
