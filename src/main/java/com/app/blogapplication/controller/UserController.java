package com.app.blogapplication.controller;

import com.app.blogapplication.Services.CustomUserDetailService;
import com.app.blogapplication.Services.UserServices;
import com.app.blogapplication.pojo.AuthorDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/blog-app/user")
@RequiredArgsConstructor
public class UserController {

    private final UserServices userServices;

    private final CustomUserDetailService customUserDetailService;

    @PostMapping("/create")
    public ResponseEntity createUser(@RequestBody AuthorDTO user) {
        try {
            user = userServices.createUser(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity getALlusers() {
        try {
            List<AuthorDTO> response = userServices.getALlusers();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userId}")
    public ResponseEntity getUserById(@PathVariable Long userId) {
        try {
            AuthorDTO response = userServices.getUserById(userId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /*this not working properly, will check later..*/
    @DeleteMapping("/{userId}")
    public ResponseEntity deleteUserById(@PathVariable Long userId) {
        try {
            userServices.deleteUserById(userId);
            return new ResponseEntity<>("user has been deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/load-user/{email}")
    public ResponseEntity loadUserbyEmail(@PathVariable  String email){
        UserDetails userDetails = customUserDetailService.loadUserByUsername(email);
        return ResponseEntity.ok(userDetails);
    }
}
