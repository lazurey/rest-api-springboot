package com.tw.bootcamp.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/")
public class GreetingController {
    private static final String GREETING_TEMPLATE = "Hello, %s";

    @RequestMapping
    public HttpEntity<Map<String, String>> index(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        HashMap<String, String> result = new HashMap<>();
        result.put("content", String.format(GREETING_TEMPLATE, name));
        return new ResponseEntity<>(result, OK);
    }
}
