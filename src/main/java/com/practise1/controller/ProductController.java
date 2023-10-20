package com.practise1.controller;

import com.practise1.model.Product;
import com.practise1.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@CrossOrigin("*")
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> list(){
        Iterable<Product> list = productService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Product>> findOne(@PathVariable Long id){
        return new ResponseEntity<>(productService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Product> create(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product){
        Optional<Product> product1 = productService.findById(id);
        if(product1.isPresent()){
            product.setId(product1.get().getId());
            productService.save(product);
            return new ResponseEntity<>(product,HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
