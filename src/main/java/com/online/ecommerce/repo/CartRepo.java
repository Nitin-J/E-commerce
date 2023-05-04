package com.online.ecommerce.repo;

import com.online.ecommerce.model.Cart;
import com.online.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepo extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
}
