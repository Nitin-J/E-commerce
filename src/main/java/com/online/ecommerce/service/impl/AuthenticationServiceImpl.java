package com.online.ecommerce.service.impl;

import com.online.ecommerce.exception.AuthenticationFailException;
import com.online.ecommerce.model.AuthenticationToken;
import com.online.ecommerce.model.User;
import com.online.ecommerce.repo.TokenRepo;
import com.online.ecommerce.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private TokenRepo tokenRepo;
    @Override
    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepo.save(authenticationToken);
    }

    @Override
    public AuthenticationToken getToken(User user) {
        return tokenRepo.findByUser(user);
    }

    @Override
    public void tokenCheck(String token) {
        //null check
        if(Objects.isNull(token)){
            throw new AuthenticationFailException("Token not Present");
        }
        if(Objects.isNull(getUser(token))){
            throw new AuthenticationFailException("Token not valid");
        }
    }

    @Override
    public User getUser(String token) {
        AuthenticationToken authenticationToken = tokenRepo.findByToken(token);
        if(Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }
}
