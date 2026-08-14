package com.bite.order.controller;

import com.bite.product.api.ProductApi;
import com.bite.product.model.ProductInfo;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/feign")
@RestController
public class FeignController {
    @Autowired
    private ProductApi productApi;
    /**
     * 通过远程调用, 访问商品服务p1
     * @param id
     * @return
     */
    @RequestMapping("/o1")
    public String o1(Integer id, String userName, HttpServletResponse response){
        log.info("接收到过滤器添加的参数, userName:{}", userName);
        response.setStatus(502);
        return productApi.p1(id);
    }
    @RequestMapping("/o2")
    public String o2(Integer id, String name){
        return productApi.p2(id,name);
    }
    @RequestMapping("/o3")
    public String o3(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(45);
        productInfo.setProductName("T恤");
        return productApi.p3(productInfo);
    }
    @RequestMapping("/o4")
    public String o4(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setId(46);
        productInfo.setProductName("T恤46");
        return productApi.p4(productInfo);
    }


}
