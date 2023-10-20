package com.practise1.service.implement;


import com.practise1.model.Bill;
import com.practise1.repository.BillRepository;
import com.practise1.service.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class BillService implements IBillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    @Override
    public void save(Bill bill) {
    billRepository.save(bill);
    }

    @Override
    public void delete(Long id) {
billRepository.delete(findById(id).get());
    }
}
