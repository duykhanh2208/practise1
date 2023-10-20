package com.practise1.repository;


import com.practise1.model.Bill;
import com.practise1.model.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    @Query(value = "select * from bill where user_id = ? ", nativeQuery = true)
    Optional<Bill> searchBillByUserID(@Param("id")Long id);
}
