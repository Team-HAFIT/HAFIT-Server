package com.feedback.hafit.global.jwt;

import com.feedback.hafit.domain.user.enumerate.Role;
import lombok.ToString;

@ToString
public class JwtAuthentication {

    public final String accessToken;
    public final String email;
    public final String role;

    public JwtAuthentication(String accessToken, String email, Role role) {
        this.accessToken = accessToken;
        this.email = email;
        this.role = String.valueOf(role);
    }

}