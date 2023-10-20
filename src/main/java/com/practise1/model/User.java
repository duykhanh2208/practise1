package com.practise1.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull

    private String account;

    @NotNull
    private String password;
    @ManyToOne
    private Role role;
    @NotNull
    private boolean status;

    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String account, String password, Role role) {
        this.account = account;
        this.password = password;
        this.role = role;
    }
}
