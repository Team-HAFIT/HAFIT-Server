package com.feedback.hafit.config;

//@Configuration
//@EnableWebSecurity
//@AllArgsConstructor
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class SecurityConfig {
//     private UserService userService;
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                // 로그인 권한은 누구나
//                .antMatchers("/user/loginPage", "/user/signupPage", "/").permitAll()
//                // admin 권한
//                .antMatchers("/admin/**").access("hasRole('ADMIN')")
//                // "/" 도메인 접근 허용
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                // 로그인 url 설정
//                .loginPage("/user/loginPage")
//                // 로그인 처리 로직 url 설정
//                .loginProcessingUrl("/user/login")
//                .usernameParameter("email")
//                .passwordParameter("password")
//                // 로그인 성공시 리다이렉트 url 설정
//                .defaultSuccessUrl("/")
//                // 로그인 실패시 리다이렉트 url 설정
//                .failureUrl("/user/access_denied")
//                // 로그아웃
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/");
//                // 인증에 실패했을 때 보여주는 화면 url, 로그인 form으로 파라미터값 error=true로 보낸다.
//        return http.build();
//    }
//
//}
