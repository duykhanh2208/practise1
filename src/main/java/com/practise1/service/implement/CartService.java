package com.practise1.service.implement;


import com.practise1.model.Cart;
import com.practise1.repository.CartRepository;
import com.practise1.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class CartService implements ICartService {
    @Autowired
    private CartRepository cartRepository;
    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public void save(Cart cart) {
    cartRepository.save(cart);
    }

    @Override
    public void delete(Long id) {
    cartRepository.delete(findById(id).get());
    }

    @Override
    public Optional<Cart> searchCart(Long id) {
        return cartRepository.searchCartByUser(id);
    }
}
