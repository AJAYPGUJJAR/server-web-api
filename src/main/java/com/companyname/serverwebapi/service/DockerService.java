package com.companyname.serverwebapi.service;

import com.companyname.serverwebapi.utils.Constants;
import com.companyname.serverwebapi.utils.ShellCommandExecutor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DockerService {

    public List<Object> getAllDockerContainers() {
        String output = ShellCommandExecutor.executeShellCommand(Constants.DOCKER_PS_JSON);
        String[] lines = output.split("\\r?\\n");
        ObjectMapper mapper = new ObjectMapper();
        List<Object> containers = new ArrayList<>();
        for (String line : lines) {
            try {
                containers.add(mapper.readValue(line, Object.class));
            } catch (IOException e) {
                containers.add("Error parsing line: " + line + " - " + e.getMessage());
            }
        }
        return containers;
    }


}
