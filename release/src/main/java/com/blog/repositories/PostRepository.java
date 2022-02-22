package com.blog.repositories;

import com.blog.pojo.Post;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@EnableScan
public interface PostRepository extends CrudRepository<Post, String> {

    List<Post> findByBlogID(String blogID);
}