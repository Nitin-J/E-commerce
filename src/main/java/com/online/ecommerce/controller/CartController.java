package com.online.ecommerce.controller;

import com.online.ecommerce.common.ApiResponse;
import com.online.ecommerce.dto.cart.CartDto;
import com.online.ecommerce.dto.cart.AddToCartDto;
import com.online.ecommerce.model.User;
import com.online.ecommerce.service.AuthenticationService;
import com.online.ecommerce.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    //post cart api
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
                                                 @RequestParam String token){
        authenticationService.tokenCheck(token);

        User user = authenticationService.getUser(token);

        cartService.addToCart(addToCartDto,user);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().success(true).message("Added to cart").build());
    }

    //get all cart item for a use
    @Operation(summary = "gets all the cart item for a user")
    @GetMapping("/getCart")
    public ResponseEntity<CartDto> getCart(@RequestParam("token") String token){
        authenticationService.tokenCheck(token);

        User user = authenticationService.getUser(token);

        CartDto cartDto = cartService.getCart(user);

        return ResponseEntity.status(HttpStatus.OK).body(cartDto);

    }

    //delete a cart item for a user
    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteItemFromCart(@PathVariable("cartItemId") Integer ItemId,
                                                          @RequestParam("token") String token){
        authenticationService.tokenCheck(token);

        User user = authenticationService.getUser(token);

        cartService.deleteFromCart(user, ItemId);

        return ResponseEntity.status(HttpStatus.OK).body(ApiResponse.builder().message("Product deleted from cart").success(true).build());
    }
}
