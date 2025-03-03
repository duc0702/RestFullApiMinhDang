package com.example.buoi01.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @GetMapping("/")
    public String getMethodName(){
        return "hello";
    }
}
