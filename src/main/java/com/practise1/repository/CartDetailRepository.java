package com.practise1.repository;

import com.practise1.model.Cart;
import com.practise1.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    @Query(value = "select * from cart_detail where cart_id=? ", nativeQuery = true)
    List<CartDetail> searchCartDetailByCartID(@Param("id")Long id);

    @Query(value = "select * from cart_detail join cart c on c.id = cart_detail.cart_id where c.user_id = ?",nativeQuery = true)
    List<CartDetail> getAllCartDetailByUser(@Param("id") Long id);

}
