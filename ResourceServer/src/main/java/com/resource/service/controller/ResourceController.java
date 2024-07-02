package com.resource.service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

	@GetMapping("/resource")
    public String getResource() {
        return "Access grated to protected resource";
    }
}
