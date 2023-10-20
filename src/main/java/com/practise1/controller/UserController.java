package com.practise1.controller;

import com.practise1.model.User;
import com.practise1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    private ResponseEntity<Iterable<User>> list() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findOne(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/findByAccount")
    public ResponseEntity<User> findByAccount(@RequestBody User user) {
        String account = user.getAccount();
        String password = user.getPassword();
        Optional<User> userFoundByAccount = userService.findUserByAccount(account, password);
        if (userFoundByAccount.isPresent()) {
            return new ResponseEntity<>(userFoundByAccount.get(), HttpStatus.OK);
        } else {
            User user1=new User("Not Found", "Not Found");
            return new ResponseEntity<>(user1,HttpStatus.OK);
        }
    }


    @PostMapping("/create" )
    public ResponseEntity<User> createUser(@RequestBody User user){
        Long role_ID = user.getRole().getId();
        if(role_ID==2){
            user.setStatus(false);
            userService.save(user);
            return new ResponseEntity<>(user,HttpStatus.OK);
        } else if(role_ID==3) {
            user.setStatus(true);
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }  return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id){
        Optional<User> user1 = userService.findById(id);
        user.setId(user1.get().getId());
        userService.save(user);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

}
