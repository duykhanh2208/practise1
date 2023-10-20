package com.practise1.service.implement;


import com.practise1.model.Cart;
import com.practise1.model.CartDetail;
import com.practise1.repository.CartDetailRepository;
import com.practise1.repository.CartRepository;
import com.practise1.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CartDetailService implements ICartDetailService {
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private CartRepository cartRepository;

    @Override
    public Iterable<CartDetail> findAll() {
        return cartDetailRepository.findAll();
    }

    @Override
    public Optional<CartDetail> findById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public void save(CartDetail cartDetail) {
        cartDetailRepository.save(cartDetail);
    }

    @Override
    public void delete(Long id) {

        cartDetailRepository.delete(findById(id).get());
    }

    @Override
    public Iterable<CartDetail> searchCartDetail(Long id) {
        return cartDetailRepository.searchCartDetailByCartID(id);
    }

    @Override
    public void addToCart(CartDetail cartDetail, Long id) {
        Long user_id = id;
        Optional<Cart> cart = cartRepository.searchCartByUser(user_id);
        if (cart.isPresent()) {
            cartDetail.setCart(cart.get());

            List<CartDetail> cartDetailList = cartDetailRepository.searchCartDetailByCartID(cart.get().getId());

            Boolean flag = true;
            for (CartDetail C : cartDetailList) {
                Long C_Product_ID = C.getProduct().getId();
                if (C_Product_ID == cartDetail.getProduct().getId()) {
                    Long oldQuantity = C.getQuantity();
                    Long addMoreQuantity = cartDetail.getQuantity();
                    Long totalQuantity = oldQuantity+addMoreQuantity;
                    C.setQuantity(totalQuantity);
                    cartDetailRepository.save(C);
                    flag = false;
                }
            }
            if (flag==true) {
                cartDetailRepository.save(cartDetail);
            }
        }
    }

}
