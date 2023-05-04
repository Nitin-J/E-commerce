package com.online.ecommerce.repo;

import com.online.ecommerce.model.AuthenticationToken;
import com.online.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepo extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByUser(User user);
    AuthenticationToken findByToken(String token);
}
