package com.blog.demo.controller;


import com.blog.api.demo.BlogServiceApi;
import com.common.demo.pojo.Result;
import com.blog.api.demo.pojo.request.AddBlogInfoRequest;
import com.blog.api.demo.pojo.request.UpBlogRequest;
import com.blog.api.demo.pojo.response.BlogInfoResponse;
import com.blog.demo.service.BlogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.backoff.BackOff;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController implements BlogServiceApi {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public Result<List<BlogInfoResponse>> getList(){
        return Result.success(blogService.getList());
    }

    @RequestMapping("/getBlogDetail")
    public Result<BlogInfoResponse> getBlogDeatail(@NotNull Integer blogId){
        log.info("getBlogDetail, blogId: {}", blogId);
        return Result.success(blogService.getBlogDeatil(blogId));
    }
    @RequestMapping("/add")
    public Result<Boolean> addBlog(@Validated @RequestBody AddBlogInfoRequest addBlogInfoRequest){
        log.info("addBlog 接收参数: "+ addBlogInfoRequest);
        return Result.success(blogService.addBlog(addBlogInfoRequest));
    }
    /**
     * 更新博客
     */
    @RequestMapping("/update")
    public Result<Boolean> updateBlog(@Valid @RequestBody UpBlogRequest upBlogRequest){
        log.info("updateBlog 接收参数: "+ upBlogRequest);
        return Result.success(blogService.update(upBlogRequest));
    }

    @RequestMapping("/delete")
    public Result<Boolean> deleteBlog(@NotNull Integer blogId){
        log.info("deleteBlog 接收参数: "+ blogId);
        return Result.success(blogService.delete(blogId));
    }
}
