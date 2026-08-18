package com.seata.account.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.seata.account.entity.AccountInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<AccountInfo> {
}
