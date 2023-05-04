package com.online.ecommerce.service;

import com.online.ecommerce.model.AuthenticationToken;
import com.online.ecommerce.model.User;

public interface AuthenticationService {
    void saveConfirmationToken(AuthenticationToken authenticationToken);

    AuthenticationToken getToken(User user);

    void tokenCheck(String token);

    User getUser(String token);
}
