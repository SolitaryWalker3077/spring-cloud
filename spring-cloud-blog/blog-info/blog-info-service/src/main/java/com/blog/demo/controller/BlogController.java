package com.blog.demo.controller;


import com.common.demo.pojo.Result;
import com.blog.api.demo.pojo.request.AddBlogInfoRequest;
import com.blog.api.demo.pojo.request.UpBlogRequest;
import com.blog.api.demo.pojo.response.BlogInfoResponse;
import com.blog.demo.service.BlogService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("/blog")
@RestController
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping("/getList")
    public List<BlogInfoResponse> getList(){
        return blogService.getList();
    }

    @RequestMapping("/getBlogDetail")
    public BlogInfoResponse getBlogDeatail(@NotNull Integer blogId){
        log.info("getBlogDetail, blogId: {}", blogId);
        return blogService.getBlogDeatil(blogId);
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
    public Boolean updateBlog(@Valid @RequestBody UpBlogRequest upBlogRequest){
        log.info("updateBlog 接收参数: "+ upBlogRequest);
        return blogService.update(upBlogRequest);

    }

    @RequestMapping("/delete")
    public Boolean deleteBlog(@NotNull Integer blogId){
        log.info("deleteBlog 接收参数: "+ blogId);
        return blogService.delete(blogId);
    }
}
