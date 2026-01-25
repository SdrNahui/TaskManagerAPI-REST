package com.example.TaskManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private List<String> nombres;
    @GetMapping
    public String getTest() {
        return "API FUNCIONANDO";
    }

    public void postTest(){

    }

}
