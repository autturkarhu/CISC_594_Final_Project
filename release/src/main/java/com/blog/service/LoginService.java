package com.blog.service;

import com.blog.entity.LoginEntity;
import com.blog.pojo.User;

public interface LoginService {
    User validateUserAccount(LoginEntity loginEntity) throws Exception;
}

