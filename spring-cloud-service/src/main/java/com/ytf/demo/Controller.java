package com.ytf.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ytf.demo.entity.User;

@RestController
public class Controller {

    @GetMapping("/getMe")
    public ResponseEntity<User> getMe() {
        System.out.println("spring cloud 演示");
        User user = new User();
        user.setUsername("spring cloud 演示");
        return ResponseEntity.ok(user);
    }
    @GetMapping("/notFound")
    public ResponseEntity<User> notFound() {
        return ResponseEntity.notFound().build();
    }
}
