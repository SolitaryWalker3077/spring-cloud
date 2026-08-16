package com.jinan.product.service;

import com.jinan.product.mapper.ProductMapper;
import com.jinan.product.model.ProductInfo;
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
