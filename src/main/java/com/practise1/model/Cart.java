package com.practise1.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;

    private boolean display=true;
    public Cart() {
    }

    public Cart(User user, boolean display) {
        this.user = user;
        this.display = display;
    }

    public Cart(User user) {
        this.user = user;
    }
}
