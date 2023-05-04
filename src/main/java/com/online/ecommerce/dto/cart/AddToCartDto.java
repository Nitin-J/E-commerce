package com.online.ecommerce.dto.cart;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class AddToCartDto {
    @JsonIgnore
    private Integer id;
    @Schema(required = true)
    private @NotNull Integer productId;
    @Schema(required = true)
    private @NotNull Integer quantity;
}
