package com.blog;

import com.blog.pojo.Blog;
import com.blog.pojo.Post;
import com.blog.pojo.User;
import com.blog.repositories.BlogRepository;
import com.blog.repositories.PostRepository;
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
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByBlogID() throws Exception {
        List<String> tokens = new ArrayList<>();
        tokens.add("Sample Token 1");

        User user = new User("Dave", "Matthews");
        user.setAccessTokens(tokens);
        userRepository.save(user);

        Blog blog1 = new Blog("Sports", "All about sports", user.getId());
        blogRepository.save(blog1);

        Post post1 = new Post("Post1", blog1.getBlogID());
        postRepository.save(post1);

        List<Post> result = postRepository.findByBlogID(post1.getBlogID());
        Assert.assertNotNull("Assert that result is not empty", result);

        for (Post post : result) {
            System.out.println(post);
        }
        Assert.assertEquals("Assert that only one element is present in the list", 1, result.size());
        postRepository.delete(result);
    }

}