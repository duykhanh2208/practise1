package com.practise1.controller;


import com.practise1.model.Revenue;
import com.practise1.service.implement.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/revenues")
public class RevenueController {
    @Autowired
    private RevenueService revenueService;

    @PostMapping("")
    public ResponseEntity<List<Object[]>> showList(@RequestBody Revenue revenue){
        int month = revenue.getMonth();
        int year = revenue.getYear();
        List<Object[]>list = revenueService.findBillDetailByFullDate(month,year);
       return new ResponseEntity<>(list,HttpStatus.OK);
    }

    @PostMapping("/showListByYear")
    public ResponseEntity<List<Object[]>> showListByYear(@RequestBody Revenue revenue){
        int year = revenue.getYear();
        List<Object[]>list = revenueService.findBillDetailByYear(year);
        return new ResponseEntity<>(list,HttpStatus.OK);
    }
}
