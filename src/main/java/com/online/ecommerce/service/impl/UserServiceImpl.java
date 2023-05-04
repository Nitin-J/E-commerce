package com.online.ecommerce.service.impl;

import com.online.ecommerce.dto.ResponseDto;
import com.online.ecommerce.dto.user.SignInDto;
import com.online.ecommerce.dto.user.SignInResponseDto;
import com.online.ecommerce.dto.user.SignupDto;
import com.online.ecommerce.exception.AuthenticationFailException;
import com.online.ecommerce.exception.CustomException;
import com.online.ecommerce.model.AuthenticationToken;
import com.online.ecommerce.model.User;
import com.online.ecommerce.repo.UserRepo;
import com.online.ecommerce.service.AuthenticationService;
import com.online.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationService authenticationService;
    @Override
    @Transactional
    public ResponseDto signup(SignupDto signupDto) {
        //check for existing user
        if(Objects.nonNull(userRepo.findByEmail(signupDto.getEmail()))){
            //user is present
            throw new CustomException("User Already Exists");
        }

        //hash the password
        String encryptPassword = signupDto.getPassword();
        try {
            encryptPassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //save the user

        User user = User.builder().
                    firstName(signupDto.getFirstName()).
                    lastName(signupDto.getLastName()).
                    email(signupDto.getEmail()).
                    password(encryptPassword).
                    build();

        userRepo.save(user);

        //create a token
        final AuthenticationToken authenticationToken = new AuthenticationToken(user);
        authenticationService.saveConfirmationToken(authenticationToken);

        ResponseDto responseDto = new ResponseDto("success","signup successFull");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }


    @Override
    public SignInResponseDto signIn(SignInDto signInDto) {
        //find user by email
        User user = userRepo.findByEmail(signInDto.getEmail());

        if(Objects.isNull(user)){
            throw new AuthenticationFailException("User not found");
        }
        //hash the password
        try {
            if (!user.getPassword().equals(hashPassword(signInDto.getPassword()))){
                throw new AuthenticationFailException("Wrong Password");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
            //compare password to db

            //if matches retrieve the token
        AuthenticationToken token = authenticationService.getToken(user);

        if (Objects.isNull(token)){
            throw new CustomException("token is not valid");
        }
        return new SignInResponseDto("success",token.getToken());
    }
}
