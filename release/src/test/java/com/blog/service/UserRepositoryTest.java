package com.blog.service;

import com.blog.pojo.User;
import com.blog.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Mock
    private UserRepository repository;

    @Test
    public void testAddUser() throws Exception {
        List<String> tokens = new ArrayList<>();
        tokens.add("Sample Token 1");

        User dave = new User("John", "Doe");
        dave.setAccessTokens(tokens);
        repository.save(dave);

        User carter = new User("Carter", "Beauford");

        carter.setAccessTokens(tokens);
        repository.save(carter);

        List<User> result = repository.findByLastName("Doe");
        Assert.assertNotNull("Assert that result is not empty", result);

        for (User user : result) {
            System.out.println(user);
            repository.delete(user);
        }
    }

    @Test
    public void testSearchByUsernameAndPassword() throws Exception {
        User user = new User("John", "Doe", "john", "admin", "john@example.com");
        repository.save(user);

        User result = repository.findByUsernameAndPassword("john", "admin");
        Assert.assertNotNull(result);
        Assert.assertEquals("Assert that password is admin", user.getPassword(), "admin");

        repository.delete(user);
    }

    @Test
    public void testSearchByUsername() throws Exception {
        User user = new User("John", "Doe", "john", "admin", "john@example.com");
        repository.save(user);

        List users = repository.findByUsername("john");
        Assert.assertNotNull(users);
    }
}