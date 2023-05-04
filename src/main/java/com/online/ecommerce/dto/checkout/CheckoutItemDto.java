package com.online.ecommerce.dto.checkout;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutItemDto {
    private String productName;
    private Integer quantity;
    private Double price;
    private Long productId;
    private Integer userId;
}
