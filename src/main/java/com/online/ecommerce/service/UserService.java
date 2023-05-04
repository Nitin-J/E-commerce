package com.online.ecommerce.service;

import com.online.ecommerce.dto.ResponseDto;
import com.online.ecommerce.dto.user.SignInDto;
import com.online.ecommerce.dto.user.SignInResponseDto;
import com.online.ecommerce.dto.user.SignupDto;

public interface UserService {
    ResponseDto signup(SignupDto signupDto);

    SignInResponseDto signIn(SignInDto signInDto);
}
