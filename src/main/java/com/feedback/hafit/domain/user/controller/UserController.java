package com.feedback.hafit.domain.user.controller;

import com.feedback.hafit.domain.user.repository.UserRepository;
import com.feedback.hafit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

//    @GetMapping("/loginPage")
//    public String loginPage() {
//        return "user/loginPage";
//    }

//    @PostMapping("/signup")
//    public String signup(@RequestBody UserFormDTO userFormDTO)  throws Exception {
//        userService.signup(userFormDTO);
//        return "회원가입 성공";
//    }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDTO userLoginDTO, HttpSession session) {
        User user = userService.login(userLoginDTO);
        if (user != null) {
            session.setAttribute("loginState", "login");
            session.setAttribute("userId", user.getUser_id());
            session.setAttribute("email", user.getEmail());
            session.setAttribute("role", user.getRole());
            System.out.println(session.getAttribute("loginState") + " " + session.getAttribute("role")+ " " + session.getAttribute("email"));
            return ResponseEntity.ok(Map.of("userId", user.getUser_id()));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PostMapping("/session")
    public Object session(HttpSession session) {
        System.out.println(session.getAttribute("userId"));
        session.setAttribute("categoryId", "1");
        return session.getAttribute("loginState");
    }

    @PostMapping("/update")
    public boolean update(@RequestBody UserDTO userDTO) {
        return userService.updateUser(userDTO);
    }

    @PostMapping("/delete")
    public boolean delete(@RequestParam String userId) {
        Long id = Long.parseLong(userId);
        return userService.deleteAccount(id);
    }

    @PostMapping("/changePassword")
    public boolean changePassword(@RequestBody UserChangePasswordDTO userChangePasswordDTO) {
        return userService.changePassword(userChangePasswordDTO);
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserInfo(@RequestParam Long userId) {
        UserDTO userDTO = userService.getUserInfoById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/emailCheck")
    @ResponseBody
    public int idCheck(@RequestParam("email") String email) {
        int cnt = userService.emailCheck(email);
        return cnt;
    }

     */
}
