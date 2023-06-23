package com.ecom.apis.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "cart_seq")
    @SequenceGenerator(name = "cart_seq",sequenceName = "cart_seq", allocationSize = 1)
    private Long cartId;
    @ManyToOne()
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne()
    @JoinColumn(name = "product_id")
    private Products products;
    private int quantity;

}
