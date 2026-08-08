package com.blog.api.demo;

import com.blog.api.demo.pojo.request.AddBlogInfoRequest;
import com.blog.api.demo.pojo.request.UpBlogRequest;
import com.blog.api.demo.pojo.response.BlogInfoResponse;
import com.common.demo.pojo.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "blog-service", path = "/blog")
public interface BlogServiceApi {
    @RequestMapping("/getList")
    Result<List<BlogInfoResponse>> getList();

    @RequestMapping("/getBlogDetail")
    Result<BlogInfoResponse> getBlogDeatail(@RequestParam("blogId") Integer blogId);
    @RequestMapping("/add")
    Result<Boolean> addBlog(@RequestBody AddBlogInfoRequest addBlogInfoRequest);

    @RequestMapping("/update")
    Result<Boolean> updateBlog(@RequestBody UpBlogRequest upBlogRequest);

    @RequestMapping("/delete")
    Result<Boolean> deleteBlog(@RequestParam("blogId") Integer blogId);
}
