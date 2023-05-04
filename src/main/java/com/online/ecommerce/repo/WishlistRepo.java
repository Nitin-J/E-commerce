package com.online.ecommerce.repo;

import com.online.ecommerce.model.User;
import com.online.ecommerce.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepo extends JpaRepository<WishList,Integer> {
    List<WishList> findAllByUserOrderByCreatedDateDesc(User user);
}
