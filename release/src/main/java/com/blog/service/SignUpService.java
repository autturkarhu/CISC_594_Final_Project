package com.blog.service;

import com.blog.entity.SignupEntity;

public interface SignUpService {
    String createUserAccount(SignupEntity signupEntity) throws Exception;

}