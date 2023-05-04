package com.online.ecommerce.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignInDto {

    private String email;

    private String password;
}
