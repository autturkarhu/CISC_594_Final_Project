package com.blog.repositories;

import com.blog.pojo.AccessToken;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AccessTokensRepository extends CrudRepository<AccessToken, String> {
    List<AccessToken> findByRole(String role);
}