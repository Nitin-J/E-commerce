package com.online.ecommerce.service;

import com.online.ecommerce.dto.cart.AddToCartDto;
import com.online.ecommerce.dto.cart.CartDto;
import com.online.ecommerce.model.User;

public interface CartService {
    void addToCart(AddToCartDto addToCartDto, User user);

    CartDto getCart(User user);

    void deleteFromCart(User user, Integer cartItemId);
}
