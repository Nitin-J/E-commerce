package com.online.ecommerce.dto.cart;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private List<CartItemDto> cartItems;
    private Double totalCost;
}
