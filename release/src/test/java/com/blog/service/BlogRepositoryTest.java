package com.blog.service;

import com.blog.pojo.Blog;
import com.blog.pojo.User;
import com.blog.repositories.BlogRepository;
import com.blog.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserID() throws Exception {
        List<String> tokens = new ArrayList<>();
        tokens.add("Sample Token 1");

        User user = new User("John", "Doe");
        user.setAccessTokens(tokens);
        userRepository.save(user);

        Blog blog1 = new Blog("Sports", "All about sports", user.getId());
        blogRepository.save(blog1);

        List<Blog> result = blogRepository.findByUserID(blog1.getUserID());
        Assert.assertNotNull("Assert that result is not empty", result);

        for (Blog blog : result) {
            System.out.println(blog);
        }
        Assert.assertEquals("Assert that only one element is present in the list", 1, result.size());
        blogRepository.delete(result.get(0));
    }

}