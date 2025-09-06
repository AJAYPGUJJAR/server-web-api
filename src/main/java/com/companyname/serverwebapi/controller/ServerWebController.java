package com.companyname.serverwebapi.controller;

import com.companyname.serverwebapi.model.ContainerActionRequest;
import com.companyname.serverwebapi.service.DockerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ResponseEntity<List<Object>> listContainers() {
        return ResponseEntity.ok(dockerService.getAllDockerContainers());
    }

    @PostMapping("/container/action")
    public ResponseEntity<String> containerAction(@RequestBody ContainerActionRequest request) {
        String progress = dockerService.executeDockerContainerAction(request);
        return ResponseEntity.ok(progress);
    }

}
