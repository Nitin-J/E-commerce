package com.online.ecommerce.controller;

import com.online.ecommerce.common.ApiResponse;
import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.model.Product;
import com.online.ecommerce.model.User;
import com.online.ecommerce.model.WishList;
import com.online.ecommerce.service.AuthenticationService;
import com.online.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private AuthenticationService authenticationService;

    //save products in wishlist
    @PostMapping("/saveWishList")
    public ResponseEntity<ApiResponse> addToWishList(@RequestBody Product product,
                                                     @RequestParam("token") String token){
        //authenticate the token
        authenticationService.tokenCheck(token);

        //find the user
        User user = authenticationService.getUser(token);
        //save the product in the user's wishlist
        WishList wishList = WishList.builder()
                .user(user)
                .product(product)
                .build();
        wishlistService.createWishList(wishList);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.builder().success(true).message("WishList Created").build());
    }

    //get all wishlist item for user
    @GetMapping("/{token}")
    public ResponseEntity<List<ProductDto>>getWishList(@PathVariable("token") String token){
        //authenticate the token
        authenticationService.tokenCheck(token);

        //find the user
        User user = authenticationService.getUser(token);

        List<ProductDto> productDtoList = wishlistService.getWishListForUser(user);

        return ResponseEntity.status(HttpStatus.OK).body(productDtoList);

    }
}
