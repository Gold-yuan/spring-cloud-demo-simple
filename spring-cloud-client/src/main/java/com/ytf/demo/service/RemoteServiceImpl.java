package com.ytf.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class RemoteServiceImpl implements RemoteService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(fallbackMethod = "error")
    public String getMe(String param) {
        return restTemplate.getForObject("http://DEMO-SERVICE/getMe", String.class);
    }

    public String error(String param) {
        
        return "error " + param;
    }
}
