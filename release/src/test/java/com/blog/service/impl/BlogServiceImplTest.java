package com.blog.service.impl;

import com.blog.entity.BlogEntity;
import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.repositories.UserRepository;
import com.blog.service.BlogService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogServiceImplTest {
    @Autowired
    private BlogService blogService;

    @Autowired
    private UserRepository userRepository;

    private BlogEntity blogEntity;

    @Before
    public void setUp() throws Exception {
        blogEntity = new BlogEntity();
        blogEntity.setBlogName("Test_Blog");
        blogEntity.setBlogDescription("This is a test blog desc");
    }

    //
    @Test
    public void createBlog() throws Exception {
        User user = new User("Test_firstname", "Test_lastname");
        userRepository.save(user);
        blogEntity.setUserID(user.getId());

        String blog = blogService.createBlog(blogEntity);
        Assert.assertNotNull("Assert that blog is created", blog);

        userRepository.delete(user);
        blogService.deleteBlog(blog);
    }

    @Test
    public void deleteBlog() throws Exception {
        String blogId = blogService.createBlog(blogEntity);
        Assert.assertNotNull("Assert that blog is created", blogId);

        Boolean result = blogService.deleteBlog(blogId);
        Assert.assertTrue("Assert that token is deleted", result);
    }

    @Test
    public void findBlogByUsedID() throws Exception {
        User user = new User("Test_firstname", "Test_lastname");
        userRepository.save(user);
        blogEntity.setUserID(user.getId());

        String blog = blogService.createBlog(blogEntity);
        Assert.assertNotNull("Assert that blog is created", blog);

        String blog1 = blogService.createBlog(blogEntity);
        Assert.assertNotNull("Assert that blog1 is created", blog1);

        List<Blog> blogs = blogService.findBlogByUserID(user.getId());
        Assert.assertFalse("Assert that blogs are returned", blogs.isEmpty());

        userRepository.delete(user);
        blogService.deleteBlog(blog1);
        blogService.deleteBlog(blog);
    }

}