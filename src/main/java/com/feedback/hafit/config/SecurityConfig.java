package com.feedback.hafit.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                // 로그인 권한은 누구나
                .antMatchers("/user/loginPage", "/user/signupPage", "/user/**", "/goal/**", "/post/**", "/category/**").permitAll()
                // admin 권한
                .antMatchers("/admin/**").access("hasRole('ADMIN')")
                // "/" 도메인 접근 허용
                .anyRequest().authenticated()
                .and()
                .formLogin()
                // 로그인 url 설정
                .loginPage("/user/loginPage")
                // 로그인 처리 로직 url 설정
                .loginProcessingUrl("/user/loginPage")
                .usernameParameter("email")
                .passwordParameter("password")
                // 로그인 성공시 리다이렉트 url 설정
                .defaultSuccessUrl("/")
                // 로그인 실패시 리다이렉트 url 설정
                .failureUrl("/")
                // 로그아웃
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
        // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
        return http.build();
    }

}
