package com.blog.demo.service;



import com.blog.api.demo.pojo.request.AddBlogInfoRequest;
import com.blog.api.demo.pojo.request.UpBlogRequest;
import com.blog.api.demo.pojo.response.BlogInfoResponse;

import java.util.List;

public interface BlogService {

    List<BlogInfoResponse> getList();

    BlogInfoResponse getBlogDeatil(Integer blogId);

    Boolean addBlog(AddBlogInfoRequest addBlogInfoRequest);

    Boolean update(UpBlogRequest upBlogRequest);

    Boolean delete(Integer blogId);
}
