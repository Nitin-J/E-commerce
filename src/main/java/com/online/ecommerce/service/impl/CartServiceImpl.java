package com.online.ecommerce.service.impl;

import com.online.ecommerce.dto.cart.AddToCartDto;
import com.online.ecommerce.dto.cart.CartDto;
import com.online.ecommerce.dto.cart.CartItemDto;
import com.online.ecommerce.exception.CustomException;
import com.online.ecommerce.model.Cart;
import com.online.ecommerce.model.Product;
import com.online.ecommerce.model.User;
import com.online.ecommerce.repo.CartRepo;
import com.online.ecommerce.service.CartService;
import com.online.ecommerce.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    ProductService productService;

    @Override
    public void addToCart(AddToCartDto addToCartDto, User user) {
        //validate if product id is valid
        Product product = productService.checkProductId(addToCartDto.getProductId());
        //save the cart
        Cart cart = Cart.builder().
                    product(product).
                    quantity(addToCartDto.getQuantity()).
                    user(user).
                    createdDate(new Date()).
                    build();

        cartRepo.save(cart);
    }

    @Override
    public CartDto getCart(User user) {
        List<Cart> cartList = cartRepo.findAllByUserOrderByCreatedDateDesc(user);
        log.info("Found " + cartList.size() + " items in cart for user " + user.getUserId());

        List<CartItemDto> cartItems = new ArrayList<>();
        Double totalCost = (double) 0;
        for (Cart cart: cartList) {
            CartItemDto cartItemDto = new CartItemDto(cart);
            if (cartItemDto.getQuantity() != null
                    && cart.getProduct() != null
                    && cart.getProduct().getPrice() != null
                    && cart.getProduct().getPrice() > 0) {
                totalCost += cartItemDto.getQuantity() * cart.getProduct().getPrice();
                cartItems.add(cartItemDto);
            }
        }

        CartDto cartDto = CartDto.builder().
                            cartItems(cartItems).
                            totalCost(totalCost).
                            build();
        log.info("Returning cart with " + cartItems.size() + " items and total cost of " + totalCost);
        return cartDto;
    }

    @Override
    public void deleteFromCart(User user, Integer cartItemId) {
        // the item id belongs to the user
        Optional<Cart> optionalCart = cartRepo.findById(cartItemId);
        log.info("cart item id for the user " +user.getUserId() +" is " +cartItemId);
        if(optionalCart.isEmpty()){
            throw new CustomException("Cart Item id is invalid "+ cartItemId);
        }
        Cart cart = optionalCart.get();

        if(cart.getUser() != user){
            throw new CustomException("Cart does not belong to the user: " +cartItemId);
        }
        cartRepo.delete(cart);
    }
}
