package com.webhacking.jpa.service;

import com.webhacking.jpa.domain.User;

public interface AuthService {
    boolean register(User user);
    boolean login(String username, String password);
}
