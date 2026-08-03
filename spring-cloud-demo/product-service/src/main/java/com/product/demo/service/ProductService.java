package com.product.demo.service;

import com.product.demo.mapper.ProductMapper;
import com.product.demo.model.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductInfo selectProductById(Integer id){
        return productMapper.selectProductById(id);
    }
}