package com.practise1.controller;

import com.practise1.model.Cart;
import com.practise1.model.CartDetail;
import com.practise1.model.User;
import com.practise1.repository.CartDetailRepository;
import com.practise1.repository.CartRepository;
import com.practise1.service.ICartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/carts")
public class CartController {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartDetailRepository cartDetailRepository;
    @Autowired
    private ICartDetailService cartDetailService;

    @GetMapping ("/cartDetail")
    public ResponseEntity <Iterable<CartDetail>> showCartDetail(){
        return new ResponseEntity<>(cartDetailRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping ("/cart")
    public ResponseEntity <Iterable<Cart>> showCart(){
        return new ResponseEntity<>(cartRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("/cart/{id}")
    public ResponseEntity<Optional<Cart>> findOneCart(@PathVariable Long id){
        return new ResponseEntity<>(cartRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping("/cartDetail/{id}")
    public ResponseEntity<Optional<CartDetail>> findOneCartDtail(@PathVariable Long id){
        return new ResponseEntity<>(cartDetailRepository.findById(id),HttpStatus.OK);
    }

    @PostMapping("/createCart")
    public ResponseEntity <Cart> saveCart(@RequestBody Cart cart){
        boolean display = true;
        cart.setDisplay(display);
        cartRepository.save(cart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PostMapping("/createCartDetail")
    public ResponseEntity <CartDetail> saveCartDetail(@RequestBody CartDetail cartDetail){
        cartDetailRepository.save(cartDetail);
        return new ResponseEntity<>(cartDetail, HttpStatus.CREATED);
    }

    @PostMapping("/updateCart/{id}")
    public ResponseEntity <Cart> updateCart(@RequestBody Cart cart, @PathVariable Long id){
      Optional<Cart>  cart1 = cartRepository.findById(id);
      if(cart1.isPresent()){
          cart.setId(cart1.get().getId());
          cartRepository.save(cart);
          return new ResponseEntity<>(cart, HttpStatus.CREATED);
      } else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    }

    @PostMapping("/updateCartDetail/{id}")
    public ResponseEntity <CartDetail> updateCartDetail(@RequestBody CartDetail cartDetail, @PathVariable Long id){
        Optional<CartDetail>  cartDetail1 = cartDetailRepository.findById(id);
        if(cartDetail1.isPresent()){
            cartDetail.setId(cartDetail1.get().getId());
            cartDetailRepository.save(cartDetail);
            return new ResponseEntity<>(cartDetail, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cart/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        cartRepository.delete(cartRepository.findById(id).get());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/cartDetail/{id}")
    public ResponseEntity<Void> deleteCartDetail(@PathVariable Long id){
        cartDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/showCart")
    public ResponseEntity<Iterable<CartDetail>> showCartDetail(@RequestBody User user){
        Long User_id = user.getId();
       Optional<Cart> cart = cartRepository.searchCartByUser(User_id);
       Long cart_id = cart.get().getId();
        Iterable<CartDetail> listCartDetail = cartDetailRepository.searchCartDetailByCartID(cart_id);
        return new ResponseEntity<>(listCartDetail,HttpStatus.OK);
    }

    @PostMapping("/addToCart/{id}")
    public ResponseEntity<CartDetail> searchCart(@RequestBody CartDetail cartDetail,
                                                     @PathVariable Long id) {
      cartDetailService.addToCart(cartDetail,id);
    return new ResponseEntity<>(cartDetail,HttpStatus.OK);
    }

    @PostMapping("/getAllCartDetailByUser/{id}")
    public ResponseEntity<Iterable<CartDetail>> getAllCartDetailByUser(@PathVariable Long id){
    Iterable<CartDetail> cartDetails = cartDetailRepository.getAllCartDetailByUser(id);
    return new ResponseEntity<>(cartDetails,HttpStatus.OK);
    }
}
