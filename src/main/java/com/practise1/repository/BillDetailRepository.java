package com.practise1.repository;


import com.practise1.model.BillDetail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Long> {
    @Query(value = "select * from bill_detail join bill b on b.id = bill_detail.bill_id\n" +
            "            join user u on u.id = b.user_id where user_id=? ", nativeQuery = true)
    Iterable<BillDetail> searchBillDetailByUser(@Param("id")Long id);

    @Query(value = "select day(date) as day, month(date) as month, year(date) as year, sum(price*quantity) as total from bill_detail group by date having month=? and year=?", nativeQuery = true)
    List<Object[]> findBillDetailByFullDate(@Param("month") int month,
                                            @Param("year")int year);


    @Query(value = "select day(date) as day, month(date) as month, year(date) as year, sum(price*quantity) as total from bill_detail group by date having year=?", nativeQuery = true)
    List<Object[]> findBillDetailByYear(@Param("year")int year);

}
