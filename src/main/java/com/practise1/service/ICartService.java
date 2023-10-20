package com.practise1.service;

import com.practise1.model.Cart;

import java.util.Optional;


public interface ICartService extends IService<Cart>  {
    Optional<Cart> searchCart(Long id);
}
