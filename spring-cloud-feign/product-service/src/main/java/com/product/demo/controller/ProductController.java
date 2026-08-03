package com.product.demo.controller;



import com.product.demo.service.ProductService;
import com.productapi.demo.api.ProductInterface;
import com.productapi.demo.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/product")
@RestController
public class ProductController implements ProductInterface {
    @Autowired
    private ProductService productService;

    @RequestMapping("/{productId}")
    public ProductInfo getProductById(@PathVariable("productId") Integer productId){
        return productService.selectProductById(productId);
    }

    @RequestMapping("/p1")
    public String p1(Integer id) {
        return "p1 接受到一个参数" + id;
    }

    @RequestMapping("/p2")
    public String p2(Integer id ,String name) {
        return "p2 接受到多个参数 id: " + id +" ,name:" + name;
    }

    @RequestMapping("/p3")
    public String p3(ProductInfo productInfo) {
        return "p3接收到对象, productInfo:"+productInfo;
    }

    @RequestMapping("/p4")
    public String p4(@RequestBody ProductInfo productInfo) {
        return "p4接收到JSON, productInfo:"+productInfo;
    }
}