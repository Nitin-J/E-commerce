package com.online.ecommerce.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "WishList")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "created_date")
    private Date createdDate;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
