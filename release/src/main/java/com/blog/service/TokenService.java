package com.blog.service;

import com.blog.entity.SignupEntity;
import com.blog.pojo.AccessToken;

public interface TokenService {


    AccessToken createAccessToken(SignupEntity signupEntity);

    TokenInfo getTokenInfo(String encryptedTokenString);

    Boolean deleteToken(AccessToken tokenToBeDeleted);

    class TokenInfo {
        public String getTokenId;
        public String getTokenString;
        public String getUsername;
        public String getRole;
        public String getPassword;
        public String getUserId;
    }
}