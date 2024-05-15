package com.webhacking.myBatis.service;

import com.webhacking.myBatis.domain.User;

public interface AuthService {
    boolean register(User user);
    boolean login(String username, String password);
}
