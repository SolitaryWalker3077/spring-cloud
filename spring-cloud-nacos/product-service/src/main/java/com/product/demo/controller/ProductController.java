package com.product.demo.controller;


import com.product.demo.model.ProductInfo;
import com.product.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping("/{productId}")
    public ProductInfo getProductById(@PathVariable("productId") Integer productId){
        return productService.selectProductById(productId);
    }
}