package com.blog.repositories;

import com.blog.pojo.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findByLastName(String lastName);

    User findByUsernameAndPassword(String userName, String password);

    List<User> findByUsername(String userName);
}