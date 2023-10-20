package com.practise1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class BillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
   private Product product;
    @NotNull
    private LocalDate date;
    @NotNull
    private Long quantity;
    @ManyToOne
    private Bill bill;
    @NotNull
    private Double price;


    public BillDetail() {
    }

    public BillDetail(Product product, LocalDate date, Long quantity, Bill bill, Double price) {
        this.product = product;
        this.date = date;
        this.quantity = quantity;
        this.bill = bill;
        this.price = price;
    }
}
