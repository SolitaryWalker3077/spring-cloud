package com.order.demo.api;


import com.productapi.demo.api.ProductInterface;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient(value = "product-service", path = "/product")
public interface ProductApi extends ProductInterface {

//    @RequestMapping("/{productId}")
//    ProductInfo getProductInfo(@PathVariable("productId") Integer productId);
//
//    @RequestMapping("/p1")
//    String p1(@RequestParam("id") Integer id);
//
//
//    @RequestMapping("/p2")
//    public String p2(@RequestParam("id") Integer id ,@RequestParam("name") String name);
//
//    @RequestMapping("/p3")
//    public String p3(@SpringQueryMap ProductInfo productInfo);
//
//    @RequestMapping("/p4")
//    public String p4(@RequestBody ProductInfo productInfo);

}
