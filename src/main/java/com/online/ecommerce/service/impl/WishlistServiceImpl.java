package com.online.ecommerce.service.impl;

import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.exception.CustomException;
import com.online.ecommerce.model.User;
import com.online.ecommerce.model.WishList;
import com.online.ecommerce.repo.WishlistRepo;
import com.online.ecommerce.service.ProductService;
import com.online.ecommerce.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {
    @Autowired
    private WishlistRepo wishlistRepo;

    @Autowired
    private ProductService productService;

    @Override
    public void createWishList(WishList wishList) {
        wishlistRepo.save(wishList);
    }

    @Override
    public List<ProductDto> getWishListForUser(User user) throws CustomException{
        final List<WishList> wishLists = wishlistRepo.findAllByUserOrderByCreatedDateDesc(user);
        if (wishLists.isEmpty()){
            throw new CustomException("Empty WishList");
        }
        List<ProductDto> productDtoList = new ArrayList<>();
        for (WishList wishList: wishLists){
            productDtoList.add(productService.getProductDto(wishList.getProduct()));
        }
        return productDtoList;
    }

}
