package com.ytf.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ytf.demo.service.RemoteService;

@RestController
public class Controller {
    
    @Autowired
    private RemoteService remoteService;

    @GetMapping("/getMe")
    public ResponseEntity<String> getMe() {
        return ResponseEntity.ok(remoteService.getMe("anything"));
    }
}
