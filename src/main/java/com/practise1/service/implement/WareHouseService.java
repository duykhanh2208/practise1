package com.practise1.service.implement;

import com.practise1.model.WareHouse;
import com.practise1.repository.WareHouseRepository;
import com.practise1.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;
@Service
public class WareHouseService implements IWareHouseService {
    @Autowired
    private WareHouseRepository wareHouseRepository;

    @Override
    public Iterable<WareHouse> findAll() {
        return wareHouseRepository.findAll();
    }

    @Override
    public Optional<WareHouse> findById(Long id) {
        return wareHouseRepository.findById(id);
    }

    @Override
    public void save(WareHouse wareHouse) {
        wareHouseRepository.save(wareHouse);
    }

    @Override
    public void delete(Long id) {
        wareHouseRepository.delete(findById(id).get());
    }
}
