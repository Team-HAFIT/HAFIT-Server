package com.feedback.hafit.entity;

import java.util.Date;

public class UserDTO {
    private int USER_NO;
    private String USER_NAME;
    private String USER_ID;
    private String USER_PW;
    private String USER_NICKNAME;
    private String USER_TEL;
    private Date USER_BIRTH;
    private char USER_SEX;
    private char USER_ROLE;
    private Date USER_CREATED;
    private char USER_STATUS;

    public UserDTO() {
        super();
    }
    public UserDTO(int USER_NO, String USER_NAME, String USER_ID, String USER_PW, String USER_NICKNAME, String USER_TEL, Date USER_BIRTH, char USER_SEX, char USER_ROLE, Date USER_CREATED, char USER_STATUS) {
        this.USER_NO = USER_NO;
        this.USER_NAME = USER_NAME;
        this.USER_ID = USER_ID;
        this.USER_PW = USER_PW;
        this.USER_NICKNAME = USER_NICKNAME;
        this.USER_TEL = USER_TEL;
        this.USER_BIRTH = USER_BIRTH;
        this.USER_SEX = USER_SEX;
        this.USER_ROLE = USER_ROLE;
        this.USER_CREATED = USER_CREATED;
        this.USER_STATUS = USER_STATUS;
    }

    public int getUSER_NO() {
        return USER_NO;
    }

    public void setUSER_NO(int USER_NO) {
        this.USER_NO = USER_NO;
    }

    public String getUSER_NAME() {
        return USER_NAME;
    }

    public void setUSER_NAME(String USER_NAME) {
        this.USER_NAME = USER_NAME;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getUSER_PW() {
        return USER_PW;
    }

    public void setUSER_PW(String USER_PW) {
        this.USER_PW = USER_PW;
    }

    public String getUSER_NICKNAME() {
        return USER_NICKNAME;
    }

    public void setUSER_NICKNAME(String USER_NICKNAME) {
        this.USER_NICKNAME = USER_NICKNAME;
    }

    public String getUSER_TEL() {
        return USER_TEL;
    }

    public void setUSER_TEL(String USER_TEL) {
        this.USER_TEL = USER_TEL;
    }

    public Date getUSER_BIRTH() {
        return USER_BIRTH;
    }

    public void setUSER_BIRTH(Date USER_BIRTH) {
        this.USER_BIRTH = USER_BIRTH;
    }

    public char getUSER_SEX() {
        return USER_SEX;
    }

    public void setUSER_SEX(char USER_SEX) {
        this.USER_SEX = USER_SEX;
    }

    public char getUSER_ROLE() {
        return USER_ROLE;
    }

    public void setUSER_ROLE(char USER_ROLE) {
        this.USER_ROLE = USER_ROLE;
    }

    public Date getUSER_CREATED() {
        return USER_CREATED;
    }

    public void setUSER_CREATED(Date USER_CREATED) {
        this.USER_CREATED = USER_CREATED;
    }

    public char getUSER_STATUS() {
        return USER_STATUS;
    }

    public void setUSER_STATUS(char USER_STATUS) {
        this.USER_STATUS = USER_STATUS;
    }
}
