package com.webhacking.jpa.controller;

import com.webhacking.jpa.domain.User;
import com.webhacking.jpa.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping("/register")
    public String showRegisterPage(User user) {
        return "register";
    }

    @PostMapping("/register")
    public String register(User user) {
        authService.register(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(String username, String password) {
        if (authService.login(username, password)) {
            return "redirect:/home";
        } else {
            return "redirect:/login?error";
        }
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
