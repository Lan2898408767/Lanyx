package com.xxxx.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/employee/advanced")
    public String adcanced () {
        return "高级资料 , 人事专员可访问" ;
    }

    @GetMapping("/employee/basic")
    public String basic () {
        return "基础资料 , admin系统管理员可访问" ;
    }
}
