package com.practice1.Student2.controller;

import com.practice1.Student2.security.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        // hardcoded on purpose, so you can focus only on JWT.
        // Next step: check a User table in MySQL with BCrypt.
        if ("admin".equals(username) && "1234".equals(password)) {
            return jwtUtil.generateToken(username);
        }
        throw new RuntimeException("Wrong username or password");
    }
}
