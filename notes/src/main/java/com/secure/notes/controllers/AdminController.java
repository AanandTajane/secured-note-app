package com.secure.notes.controllers;

import com.secure.notes.dto.UserDto;
import com.secure.notes.models.User;
import com.secure.notes.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping("/getusers")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(),
                HttpStatus.OK);
    }

    @PutMapping("/update-role")
     public ResponseEntity<String> updateUserRole(@RequestParam Long userId,
                                                    @RequestParam String roleName){
           userService.updateUserRole(userId, roleName);
           return ResponseEntity.ok("User Role Updated");

    }
     @GetMapping ("/user/{id}")
      public ResponseEntity<UserDto>  getUser(@PathVariable Long id){
                return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);

     }





}
