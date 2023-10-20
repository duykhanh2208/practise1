package com.practise1.model;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    public Bill(User user) {
        this.user = user;
    }

    public Bill() {
    }
}
