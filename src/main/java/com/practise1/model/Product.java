package com.practise1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private Double price;

    @NotNull
    private LocalDate date;
    @NotNull
    private String description;

    @NotNull
    private String provider;
    @NotNull
    private Long quantity;
    @ManyToOne
    private WareHouse wareHouse;

    public Product() {
    }
}
