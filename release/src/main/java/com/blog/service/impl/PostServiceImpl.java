package com.blog.service.impl;

import com.blog.entity.PostEntity;
import com.blog.pojo.Post;
import com.blog.repositories.PostRepository;
import com.blog.service.PostService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    private Log log = LogFactory.getLog(PostServiceImpl.class);

    public PostServiceImpl() throws NoSuchPaddingException, NoSuchAlgorithmException {

    }

    @Override
    public String createPost(PostEntity postEntity) throws Exception {
        Post toCreate = new Post();
        toCreate.setContent(postEntity.getContent());
        toCreate.setBlogID(postEntity.getBlogID());

        postRepository.save(toCreate);
        return toCreate.getPostID();
    }

    @Override
    public Boolean deletePost(String postDelete) {
        try {
            postRepository.delete(postDelete);
            return Boolean.TRUE;
        } catch (Exception e) {
            log.error(e);
        }
        return Boolean.FALSE;
    }
}