package com.blog.service;

import com.blog.entity.BlogEntity;
import com.blog.pojo.Blog;

import java.util.List;

public interface BlogService {

    String createBlog(BlogEntity blogEntity);

    Boolean deleteBlog(String blog);

    List<Blog> findBlogByUserID(String userID);
}