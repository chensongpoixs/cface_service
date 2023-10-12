package com.exadel.frs.core.trainservice.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.exadel.frs.core.trainservice.system.global.Constants.API_V1;

@RestController
@RequestMapping(API_V1 + "/chensong")
@RequiredArgsConstructor
public class TestController {
    @GetMapping(value = "/hello", name = "返回Hello World")
    public String hello(@RequestParam(value = "name", required = false) String name)
    {
//        log.info();
        return String.format("Hello %s! ", name == null ? "World" : name);
    }
}
