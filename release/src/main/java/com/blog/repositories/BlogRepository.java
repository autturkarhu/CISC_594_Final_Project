package com.blog.repositories;

import com.blog.pojo.Blog;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface BlogRepository extends CrudRepository<Blog, String> {

    List<Blog> findByUserID(String userID);

}