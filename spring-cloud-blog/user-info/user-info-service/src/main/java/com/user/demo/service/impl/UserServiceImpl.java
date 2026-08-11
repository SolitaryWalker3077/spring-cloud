package com.user.demo.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blog.api.demo.BlogServiceApi;
import com.blog.api.demo.pojo.response.BlogInfoResponse;
import com.common.demo.exception.BlogException;
import com.common.demo.pojo.Result;
import com.common.demo.utils.*;
import com.user.api.demo.pojo.request.UserInfoRegisterRequest;
import com.user.api.demo.pojo.request.UserInfoRequest;
import com.user.api.demo.pojo.response.UserInfoResponse;
import com.user.api.demo.pojo.response.UserLoginResponse;
import com.user.demo.convert.BeanConvert;
import com.user.demo.dataobject.UserInfo;
import com.user.demo.mapper.UserInfoMapper;
import com.user.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BlogServiceApi blogServiceApi;

    @Autowired
    private Redis redis;

    //超时时间为2周
    private static final long EXPIRE_TIME = 14 * 24 * 60* 60;
    private static final String USER_PREFIX = "user";

    @Override
    public UserLoginResponse login(UserInfoRequest user) {
        //验证账号密码是否正确
        UserInfo userInfo = queryUserInfo(user.getUserName());
        if (userInfo==null || userInfo.getId()==null){
            throw new BlogException("用户不存在");
        }
//        if (!user.getPassword().equals(userInfo.getPassword())){
//            throw new BlogException("用户密码不正确");
//        }
        if (!SecurityUtil.verify(user.getPassword(),userInfo.getPassword())){
            throw new BlogException("用户密码不正确");
        }
        //账号密码正确的逻辑
        Map<String,Object> claims = new HashMap<>();
        claims.put("id", userInfo.getId());
        claims.put("name", userInfo.getUserName());

        String jwt = JWTUtils.genJwt(claims);
        return new UserLoginResponse(userInfo.getId(), jwt);
    }

    @Override
    public UserInfoResponse getUserInfo(Integer userId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();
        UserInfo userInfo = selectUserInfoById(userId);
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public UserInfoResponse selectAuthorInfoByBlogId(Integer blogId) {
        UserInfoResponse userInfoResponse = new UserInfoResponse();

        //1. 根据博客ID, 获取作者ID
        Result<BlogInfoResponse> blogDeatail = blogServiceApi.getBlogDeatail(blogId);

        //2. 根据作者ID, 获取作者信息
        if (blogDeatail == null || blogDeatail.getData() == null){
            throw new BlogException("博客不存在");
        }
        UserInfo userInfo = selectUserInfoById(blogDeatail.getData().getUserId());
        BeanUtils.copyProperties(userInfo, userInfoResponse);
        return userInfoResponse;
    }

    @Override
    public Integer register(UserInfoRegisterRequest registerRequest) {
        checkUserInfo(registerRequest);
        //用户注册插入数据库
        UserInfo userInfo = BeanConvert.convertUserInfoByEncrypt(registerRequest);

        try {
            int row = userInfoMapper.insert(userInfo);
            if(row == 1) {
                //存储数据到redis中
                //redis 存储失败, 会导致查询时查不到信息, 那么就从数据库中去查询, 此处异常不处理
                redis.set(redis.buildKey(USER_PREFIX, userInfo.getUserName()),
                        JSONUtils.toJSON(userInfo),
                        EXPIRE_TIME);
                return userInfo.getId();
            }else {
                throw new BlogException("用户注册失败");
            }
        }catch (Exception e) {
            log.error("用户注册失败,e",e);
            throw new BlogException("用户注册失败");
        }
    }

    private UserInfo queryUserInfo(String userName) {
        //先从Redis当中获取数据
        String key = redis.buildKey(userName);
        //看key是否存在
        boolean exists = redis.hasKey(key);
        if(exists) {
            //从Redis中获取数据
            log.info("从redis中获取数据, key:{}", key);
            String userJson = redis.get(key);
            //将字符串转为对象
            UserInfo userInfo = JSONUtils.parseJson(userJson, UserInfo.class);
            return userInfo == null ? selectUserInfoByName(userName):userInfo;
        }else {
            //从数据库当中获取数据
            log.info("从mysql中获取数据, userName:{}", userName);
            UserInfo userInfo = selectUserInfoByName(userName);
            //把数据库的数据存储到Redis当中
            redis.set(key,JSONUtils.toJSON(userInfo),EXPIRE_TIME);
            return userInfo;
        }
    }



    private void checkUserInfo(UserInfoRegisterRequest param) {
        //用户名不能重复
        UserInfo userInfo = selectUserInfoByName(param.getUserName());
        if(userInfo != null) {
            throw new BlogException("用户名已存在");
        }
        //邮箱格式, url格式
        if(!RegexUtil.checkMail(param.getEmail())) {
            throw new BlogException("邮箱格式不合法");
        }
        if(!RegexUtil.checkURL(param.getGithubUrl())) {
            throw new BlogException("githubUrl格式不合法");
        }
    }

    public UserInfo selectUserInfoByName(String userName) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getUserName, userName).eq(UserInfo::getDeleteFlag, 0));
    }
    private UserInfo selectUserInfoById(Integer userId) {
        return userInfoMapper.selectOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getId, userId).eq(UserInfo::getDeleteFlag, 0));
    }



}
