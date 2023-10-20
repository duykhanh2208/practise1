package com.practise1.controller;

import com.practise1.model.WareHouse;
import com.practise1.service.IWareHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/wareHouses")
public class WareHouseController {
    @Autowired
    private IWareHouseService WareHouseService;

    @GetMapping("")
    public ResponseEntity<Iterable<WareHouse>> showList(){
        return new ResponseEntity<>(WareHouseService.findAll(), HttpStatus.OK);
    }

}
