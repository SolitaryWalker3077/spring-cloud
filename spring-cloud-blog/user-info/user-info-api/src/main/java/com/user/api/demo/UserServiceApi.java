package com.user.api.demo;


import com.common.demo.pojo.Result;
import com.user.api.demo.pojo.request.UserInfoRequest;
import com.user.api.demo.pojo.response.UserInfoResponse;
import com.user.api.demo.pojo.response.UserLoginResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "user-service", path = "/user")
public interface UserServiceApi {

    @RequestMapping("/login")
    Result<UserLoginResponse> login(@Validated @RequestBody UserInfoRequest user);
    @RequestMapping("/getUserInfo")
    Result<UserInfoResponse> getUserInfo(@RequestParam("userId") Integer userId);
    @RequestMapping("/getAuthorInfo")
    Result<UserInfoResponse> getAuthorInfo(@RequestParam("blogId") Integer blogId);
}
