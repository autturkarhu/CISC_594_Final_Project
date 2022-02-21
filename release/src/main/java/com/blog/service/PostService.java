package com.blog.service;

import com.blog.entity.PostEntity;

public interface PostService {

    String createPost(PostEntity postEntity) throws Exception;

    Boolean deletePost(String post);
}