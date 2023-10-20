package com.live.debate.resolver.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resolve")
public class ResolverController {

    // POST Completed round -> resolved

    // GET for testing

    @GetMapping
    String justTestin(){
        return "Yup, it's a test";
    }
}
