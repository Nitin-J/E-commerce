package com.online.ecommerce.service;

import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.model.User;
import com.online.ecommerce.model.WishList;

import java.util.List;

public interface WishlistService {

    void createWishList(WishList wishList);

    List<ProductDto> getWishListForUser(User user);
}
