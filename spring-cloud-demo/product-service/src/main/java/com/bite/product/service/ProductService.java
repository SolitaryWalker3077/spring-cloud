package com.bite.product.service;

import com.bite.product.mapper.ProductMapper;
import com.bite.product.model.ProductInfo;
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
