package com.bite.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RequestMapping("/config")
@RestController
public class ConfigController {
    @Value("${data.env}")
    private String env;

    @Value("${data.password}")
    private String password;

    @RequestMapping("/getEnv")
    public String getEnv(){
        return "env:"+ env;
    }


    @RequestMapping("/getPassword")
    public String getPassword() {
        return "password:"+password;
    }
}
