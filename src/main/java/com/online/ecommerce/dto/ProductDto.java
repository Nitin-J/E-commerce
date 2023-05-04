package com.online.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    //for create it is optional
    //for update it is necessary
    private Integer id;

    private @NotNull String name;

    private @NotNull String url;

    private @NotNull Double price;

    private @NotNull String description;

    private @NotNull Integer categoryId;

}
