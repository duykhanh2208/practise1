package com.practise1.service;

import com.practise1.model.CartDetail;
import org.springframework.data.repository.query.Param;

public interface ICartDetailService extends IService<CartDetail>  {
    Iterable<CartDetail> searchCartDetail(Long id);

    void addToCart(CartDetail cartDetail, Long id);
}
