package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.examly.springapp.model.AuthUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.model.dto.UserDTO;
import com.examly.springapp.service.UserServiceImpl;


/**
 * AuthController is a REST controller that handles user authentication and
 * registration.
 * It provides endpoints for user registration and login.
 */

@RestController
@RequestMapping("/api")
public class AuthController {

        @Autowired
        private UserServiceImpl userServiceImpl;

        @PostMapping("/register")
        public ResponseEntity<UserDTO> registerUser(@RequestBody UserDTO user) {
                return ResponseEntity.status(201).body(userServiceImpl.registerUser(user));
        }

        @PostMapping("/login")
        public ResponseEntity<AuthUser> loginUser(@RequestBody UserDTO user) {
                return ResponseEntity.status(200).body(userServiceImpl.loginUser(user));
        }

        @GetMapping("/user/{userId}")      
        public ResponseEntity<UserDTO> getUserDetails(@PathVariable Long userId) {
                UserDTO obj = userServiceImpl.getUserDetails(userId);
                if (obj != null) {
                        return ResponseEntity.status(200).body(obj);
                } else {
                        return ResponseEntity.status(404).build();
                }
        }

}