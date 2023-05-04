package com.feedback.hafit.entity;

import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
package com.feedback.hafit.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num; //sequence, auto_increment
    private Long user_num; //sequence, auto_increment

    @Column(nullable = false, unique = true, length = 30)
    private String email; //아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(length = 30)
    private String birth; //생일

    @Column(nullable = false, unique = true, length = 30)
    private String phone; // 전화

    @Column(length = 10)
    private String sex;

    @Column(nullable = false, length = 50)
    private String name; // 이름

    @Column(length = 50)
    private String nickname; //닉네임

    @Enumerated(EnumType.STRING)
    // @Column(columnDefinition = "String default ROLE_USER")
    private Role role; // 권한

    private LocalDateTime created_at; // 날짜

    @PrePersist // DB에 INSERT 되기 직전에 실행. 즉 DB에 값을 넣으면 자동으로 실행됨
    public void setCreated_at() {
        this.created_at = LocalDateTime.now();
    }

    @Builder
    public User(String email, String password, String birth, String phone, String sex, String name, String nickname, Role role, LocalDateTime created_at) {
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

    public static User createUser(UserFormDTO userFormDTO, PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .email(userFormDTO.getEmail())
                .password(passwordEncoder.encode(userFormDTO.getPassword()))
                .name(userFormDTO.getName())
                .phone(userFormDTO.getPhone())
                .role(Role.USER)
                .build();
        return user;
    }
//    public static User createUser(UserFormDTO userFormDTO, PasswordEncoder passwordEncoder) {
//        User user = User.builder()
//                .email(userFormDTO.getEmail())
//                .password(passwordEncoder.encode(userFormDTO.getPassword()))
//                .name(userFormDTO.getName())
//                .phone(userFormDTO.getPhone())
//                .role(Role.USER)
//                .build();
//        return user;
//    }

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
