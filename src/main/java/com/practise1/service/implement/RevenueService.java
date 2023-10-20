package com.practise1.service.implement;


import com.practise1.repository.BillDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RevenueService {
    @Autowired
    private BillDetailRepository billDetailRepository;

    public  List<Object[]> findBillDetailByFullDate(int month, int year){
        List<Object[]> list = billDetailRepository.findBillDetailByFullDate(month, year);
        return list;
    }
    public  List<Object[]> findBillDetailByYear(int year){
        List<Object[]> list1 = billDetailRepository.findBillDetailByYear(year);
        return list1;
    }


}
