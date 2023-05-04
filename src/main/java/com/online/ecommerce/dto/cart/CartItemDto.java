package com.online.ecommerce.dto.cart;

import com.online.ecommerce.dto.ProductDto;
import com.online.ecommerce.model.Cart;
import com.online.ecommerce.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CartItemDto {
    private Integer id;
    private Integer quantity;

    private Product product;

    public CartItemDto(Cart cart) {
        this.quantity = cart.getQuantity();
        this.product = cart.getProduct();
    }
}
