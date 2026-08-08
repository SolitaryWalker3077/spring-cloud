package com.blog.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.blog.demo.dataobject.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BlogMapper extends BaseMapper<BlogInfo> {
}
