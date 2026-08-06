package com.order.demo.controller;



import com.productapi.demo.api.ProductApi;
import com.productapi.demo.model.ProductInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/feign")
public class FeignController {
    @Autowired
    private ProductApi productApi;

    @RequestMapping("/o1")
    public String o1(Integer id) {
        return productApi.p1(id);
    }

    @RequestMapping("/o2")
    public String o2(Integer id ,String name) {
        return productApi.p2(id, name);
    }

    @RequestMapping("/o3")
    public String o3(ProductInfo productInfo) {
        productInfo.setId(1);
        productInfo.setProductName("T恤");
        return productApi.p3(productInfo);
    }

    @RequestMapping("/o4")
    public String o4(@RequestBody ProductInfo productInfo) {
        productInfo.setId(1);
        productInfo.setProductName("T恤");
        return productApi.p3(productInfo);
    }
}
