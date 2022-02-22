package com.blog.service.impl;

import com.blog.entity.BlogEntity;
import com.blog.entity.PostEntity;
import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.repositories.BlogRepository;
import com.blog.repositories.UserRepository;
import com.blog.service.PostService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceImplTest {

    @Autowired
    private PostService postService;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    private BlogEntity blogEntity;
    private PostEntity postEntity;

    @Before
    public void setUp() throws Exception {
        blogEntity = new BlogEntity();
        postEntity = new PostEntity();
        postEntity.setContent("This is a test post");
    }

    @Test
    public void createPost() throws Exception {
        User user = new User("Test_firstname", "Test_lastname");
        userRepository.save(user);
        blogEntity.setUserID(user.getId());

        Blog blog = new Blog("Test_blog", "This is test blog", blogEntity.getUserID());
        blogRepository.save(blog);
        postEntity.setBlogID(blog.getBlogID());

        String post = postService.createPost(postEntity);
        Assert.assertNotNull("Assert that post is created", post);
    }

    @Test
    public void deletePost() throws Exception {
        User user = new User("Test_firstname", "Test_lastname");
        userRepository.save(user);
        blogEntity.setUserID(user.getId());

        Blog blog = new Blog("Test_blog", "This is test blog", blogEntity.getUserID());
        blogRepository.save(blog);
        postEntity.setBlogID(blog.getBlogID());

        String postId = postService.createPost(postEntity);
        Assert.assertNotNull("Assert that post is created", postId);

        Boolean result = postService.deletePost(postId);
        Assert.assertTrue("Assert that token is deleted", result);
    }

}