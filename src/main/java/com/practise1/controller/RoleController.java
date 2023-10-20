package com.practise1.controller;

import com.practise1.model.Role;
import com.practise1.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private IRoleService RoleService;

    @GetMapping ("")
    public ResponseEntity<Iterable<Role>> showList(){
        return new ResponseEntity<>(RoleService.findAll(), HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody Role role){
        RoleService.save(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        RoleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
