package com.practice1.Student2.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        String header = request.getHeader("Authorization");           // 1. read the header

        if (header != null && header.startsWith("Bearer ")) {         // 2. is it a Bearer token?
            String token = header.substring(7);                       //    cut off "Bearer "
            try {
                String username = jwtUtil.extractUsername(token);     // 3. validate the token
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(username, null, new ArrayList<>());
                SecurityContextHolder.getContext().setAuthentication(auth); // 4. mark user as logged in
            } catch (Exception e) {
                // invalid or expired token: do nothing, the request stays locked
            }
        }
        chain.doFilter(request, response);                            // 5. always continue
    }
}
