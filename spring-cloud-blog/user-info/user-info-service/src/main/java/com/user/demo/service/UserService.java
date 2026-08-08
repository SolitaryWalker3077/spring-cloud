package com.user.demo.service;


import com.user.api.demo.pojo.request.UserInfoRequest;
import com.user.api.demo.pojo.response.UserInfoResponse;
import com.user.api.demo.pojo.response.UserLoginResponse;

public interface UserService {
    UserLoginResponse login(UserInfoRequest user);

    UserInfoResponse getUserInfo(Integer userId);

    UserInfoResponse selectAuthorInfoByBlogId(Integer blogId);
}
