package com.practise1.service.implement;

import com.practise1.model.BillDetail;
import com.practise1.repository.BillDetailRepository;
import com.practise1.service.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service

public class BillDetailService implements IBillDetailService {
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Override
    public Iterable<BillDetail> findAll() {

        return billDetailRepository.findAll();
    }

    @Override
    public Optional<BillDetail> findById(Long id) {

        return billDetailRepository.findById(id);
    }

    @Override
    public void save(BillDetail billDetail) {
    billDetailRepository.save(billDetail);
    }

    @Override
    public void delete(Long id) {
    billDetailRepository.delete(billDetailRepository.findById(id).get());
    }
}
