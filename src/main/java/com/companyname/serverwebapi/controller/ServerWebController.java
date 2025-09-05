package com.companyname.serverwebapi.controller;

import com.companyname.serverwebapi.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ServerWebController {

    @Autowired
    DockerService dockerService;

    @GetMapping("/")
    public String hello() {
        return "Hello, World!";
    }

    @GetMapping("/containers" )
    public List<Object> listContainers() {
        return dockerService.getAllDockerContainers();
    }

}
