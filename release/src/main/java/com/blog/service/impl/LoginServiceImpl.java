package com.blog.service.impl;

import com.blog.encryptionUtils.EncryptionDecryptionAES;
import com.blog.entity.LoginEntity;
import com.blog.pojo.User;
import com.blog.repositories.UserRepository;
import com.blog.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Service
public class LoginServiceImpl implements LoginService{

    private EncryptionDecryptionAES encryptionUtils = new EncryptionDecryptionAES();
    @Autowired
    private UserRepository userRepository;

    public LoginServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException {
    }

    public User validateUserAccount(LoginEntity loginEntity) throws Exception
    {
        if(StringUtils.isNotBlank(loginEntity.getUsername()) && StringUtils.isNotBlank(loginEntity.getPassword()))
        {
            User validated = userRepository.findByUsernameAndPassword(loginEntity.getUsername(), encryptionUtils.decrypt(loginEntity.getPassword()));
            return validated;
        }
        return null;
    }
}