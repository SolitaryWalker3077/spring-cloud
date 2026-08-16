package com.jinan.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class NacosController {
    @Value("${nacos.config:default_value}")
    private String nacosConfg;

    @RequestMapping("/getConfig")
    public String getConfig(){
        return "从Nacos获取配置项nacos.config:"+nacosConfg;
    }
}
