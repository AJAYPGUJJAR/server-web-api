package com.companyname.serverwebapi.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContainerActionRequest {
    private String containerName;
    private String command;
}

