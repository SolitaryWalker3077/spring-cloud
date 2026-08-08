package com.user.api.demo.pojo.response;

import lombok.Data;

@Data
public class UserInfoResponse {
    private Integer id;
    private String userName;
    private String githubUrl;
}
