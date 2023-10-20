package com.practise1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Product product;
    @NotNull
    private Long quantity;
    @NotNull
    private LocalDate buyDate;
    @ManyToOne
    private Cart cart;

    public CartDetail() {
    }

    public CartDetail(Product product, Long quantity, LocalDate buyDate, Cart cart) {
        this.product = product;
        this.quantity = quantity;
        this.buyDate = buyDate;
        this.cart = cart;
    }
}
