package com.webhacking.myBatis.repository;

import com.webhacking.myBatis.domain.User;

public interface UserRepository {
    User findByUsername(String username);
    void save(User user);
}
