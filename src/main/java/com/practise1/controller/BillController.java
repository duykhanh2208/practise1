package com.practise1.controller;

import com.practise1.model.*;
import com.practise1.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/bills")
public class BillController {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private BillDetailRepository billDetailRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping ("/billDetail")
    public ResponseEntity<Iterable<BillDetail>> showBillDetail(){
        return new ResponseEntity<>(billDetailRepository.findAll(), HttpStatus.OK);
    }
    @PostMapping("/searchBillDetailByUserID/{user_id}")
    public ResponseEntity<Iterable<BillDetail>> searchBillDetailByUserID(@PathVariable ("user_id") Long id){
        return new ResponseEntity<>(billDetailRepository.searchBillDetailByUser(id), HttpStatus.OK);
    }


    @RequestMapping ("/bill")
    public ResponseEntity <Iterable<Bill>> showBill(){
        return new ResponseEntity<>(billRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping("/bill/{id}")
    public ResponseEntity<Optional<Bill>> findOneBill(@PathVariable Long id){
        return new ResponseEntity<>(billRepository.findById(id),HttpStatus.OK);
    }

    @RequestMapping("/billDetail/{id}")
    public ResponseEntity<Optional<BillDetail>> findOneBillDtail(@PathVariable Long id){
        return new ResponseEntity<>(billDetailRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping("/createBill")
    public ResponseEntity <Bill> saveBill(@RequestBody Bill bill){
        billRepository.save(bill);
        return new ResponseEntity<>(bill, HttpStatus.CREATED);
    }

    @PostMapping("/createBillDetail")
    public ResponseEntity <BillDetail> saveBillDetail(@RequestBody BillDetail billDetail){
        billDetailRepository.save(billDetail);
        return new ResponseEntity<>(billDetail, HttpStatus.CREATED);
    }

    @PostMapping("/updateBill/{id}")
    public ResponseEntity <Bill> updateBill(@RequestBody Bill bill, @PathVariable Long id){
        Optional<Bill>  bill1 = billRepository.findById(id);
        if(bill1.isPresent()){
            bill.setId(bill1.get().getId());
            billRepository.save(bill);
            return new ResponseEntity<>(bill, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/updateBillDetail/{id}")
    public ResponseEntity <BillDetail> updateBillDetail(@RequestBody BillDetail billDetail, @PathVariable Long id){
        Optional<BillDetail>  billDetail1 = billDetailRepository.findById(id);
        if(billDetail1.isPresent()){
            billDetail.setId(billDetail1.get().getId());
            billDetailRepository.save(billDetail);
            return new ResponseEntity<>(billDetail, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bill/{id}")
    public ResponseEntity<Void> deleteBill(@PathVariable Long id){
        billRepository.delete(billRepository.findById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/billDetail/{id}")
    public ResponseEntity<Void> deleteBillDetail(@PathVariable Long id){
        billDetailRepository.delete(billDetailRepository.findById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
@PostMapping("/saveBillDetail/{id}")
    public ResponseEntity<Void> saveBillDetailAndBill(@PathVariable Long id){
    Iterable<CartDetail> cartDetails = cartDetailRepository.getAllCartDetailByUser(id);
    User user = userRepository.findById(id).get();
    Bill bill = new Bill(user);
    billRepository.save(bill);

    for (CartDetail c:cartDetails) {
        Long quantity =c.getQuantity();
        LocalDate date = LocalDate.now();
        Product product = c.getProduct();
        Double price = c.getProduct().getPrice();
        BillDetail  billDetail = new BillDetail(product,date,quantity,bill,price);
        billDetailRepository.save(billDetail);
        cartDetailRepository.delete(c);
    }
    return new ResponseEntity<>(HttpStatus.OK);
}
}
