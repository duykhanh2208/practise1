package com.practise1.repository;

import com.practise1.model.Cart;
import com.practise1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    @Query(value = "select * from cart where user_id=? ", nativeQuery = true)
    Optional<Cart> searchCartByUser(@Param("id")Long id);
}
