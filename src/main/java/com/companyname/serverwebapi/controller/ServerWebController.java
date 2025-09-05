package com.companyname.serverwebapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerWebController {

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

}
