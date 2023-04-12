package com.feedback.hafit.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_NO")
    private Integer userNo;

    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @Column(name = "USER_ID", unique = true, nullable = false)
    private String userId;

    @Column(name = "USER_PW", nullable = false)
    private String userPw;

    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @Column(name = "USER_TEL", unique = true, nullable = false)
    private String userTel;

    @Column(name = "USER_BIRTH", nullable = false)
    private LocalDate userBirth;

    @Column(name = "USER_SEX", nullable = false)
    private String userSex;

    @Column(name = "USER_ROLE", nullable = false)
    private String userRole;

    @Column(name = "USER_CREATED", nullable = false)
    private LocalDate userCreated;

    @Column(name = "USER_STATUS", nullable = false)
    private String userStatus;

    // Getter and Setter


    public Integer getUserNo() {
        return userNo;
    }

    public void setUserNo(Integer userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPw() {
        return userPw;
    }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public LocalDate getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(LocalDate userBirth) {
        this.userBirth = userBirth;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public LocalDate getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(LocalDate userCreated) {
        this.userCreated = userCreated;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }
}

