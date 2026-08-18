package com.seata.storage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seata.storage.entity.StorageInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StorageMapper extends BaseMapper<StorageInfo> {

}
