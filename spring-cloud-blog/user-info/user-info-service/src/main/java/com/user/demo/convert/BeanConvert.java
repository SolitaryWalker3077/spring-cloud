package com.user.demo.convert;

import com.common.demo.utils.SecurityUtil;
import com.mysql.cj.protocol.Security;
import com.user.api.demo.pojo.request.UserInfoRegisterRequest;
import com.user.demo.dataobject.UserInfo;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;

public class BeanConvert {

    public static UserInfo convertUserInfoByEncrypt(UserInfoRegisterRequest registerRequest) {
        //用户注册插入数据库
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(registerRequest.getUserName());
        userInfo.setPassword(SecurityUtil.encrypt(registerRequest.getPassword()));
        userInfo.setGithubUrl(registerRequest.getGithubUrl());
        userInfo.setEmail(registerRequest.getEmail());
        return userInfo;
    }
}
