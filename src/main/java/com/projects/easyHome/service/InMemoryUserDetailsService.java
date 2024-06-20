package com.projects.easyHome.service;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final Map<String, UserDetails> users = new HashMap<>();
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public InMemoryUserDetailsService(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostConstruct // Initialize users on bean creation
    public void init() {
        UserDetails user1 = User.withUsername("pavan")
                .passwordEncoder(bCryptPasswordEncoder::encode)
                .password("password")
                .roles("USER")
                .build();

        UserDetails user2 = User.withUsername("admin")
                .passwordEncoder(bCryptPasswordEncoder::encode)
                .password("admin")
                .roles("ADMIN")
                .build();

        users.put(user1.getUsername(), user1);
        users.put(user2.getUsername(), user2);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = users.get(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}