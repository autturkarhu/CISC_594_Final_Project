package com.blog.service.impl;

import com.blog.entity.BlogEntity;
import com.blog.pojo.Blog;
import com.blog.repositories.BlogRepository;
import com.blog.repositories.UserRepository;
import com.blog.service.BlogService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    private Log log = LogFactory.getLog(BlogServiceImpl.class);

    public BlogServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException {

    }

    @Override
    public String createBlog(BlogEntity blogEntity) {
        Blog toCreate = new Blog();
        toCreate.setBlogName(blogEntity.getBlogName());
        toCreate.setBlogDescription(blogEntity.getBlogDescription());
        toCreate.setUserID(blogEntity.getUserID());

        try {
            blogRepository.save(toCreate);
            return toCreate.getBlogID();
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

    @Override
    public Boolean deleteBlog(String blogDelete) {
        try {
            blogRepository.delete(blogDelete);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error(e);
        }
        return Boolean.FALSE;
    }

    @Override
    public List<Blog> findBlogByUserID(String userID) {
        try {
            List<Blog> byUserID = blogRepository.findByUserID(userID);
            return byUserID;
        } catch (Exception e) {
            log.error(e);
        }
        return null;
    }

}