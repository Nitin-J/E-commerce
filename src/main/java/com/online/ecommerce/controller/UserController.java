package com.online.ecommerce.controller;

import com.online.ecommerce.dto.ResponseDto;
import com.online.ecommerce.dto.user.SignInDto;
import com.online.ecommerce.dto.user.SignInResponseDto;
import com.online.ecommerce.dto.user.SignupDto;
import com.online.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto){
        return userService.signup(signupDto);
    }

    @PostMapping("/signIn")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto){
        return userService.signIn(signInDto);
    }
}
