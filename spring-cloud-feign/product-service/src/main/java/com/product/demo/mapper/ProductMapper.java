package com.product.demo.mapper;


import com.productapi.demo.model.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ProductMapper {
    @Select("select * from product_detail where id = #{id}")
    ProductInfo selectProductById(Integer id);
}