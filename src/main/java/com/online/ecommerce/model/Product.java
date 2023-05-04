package com.online.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id", nullable = false)
    private Integer ProductId;

    private @NotNull String name;

    private @NotNull String url;

    private @NotNull Double price;

    private @NotNull String description;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id")
    Category category;
}
