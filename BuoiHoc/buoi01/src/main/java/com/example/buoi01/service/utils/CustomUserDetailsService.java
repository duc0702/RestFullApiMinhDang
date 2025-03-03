package com.example.buoi01.service.utils;

import com.example.buoi01.domain.User;
import com.example.buoi01.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userService.getUserByEmail(username,User.class);
        if (user.isEmpty()){
            throw  new UsernameNotFoundException("User not found");
        }
        User userEntity = user.get();
        return new org.springframework.security.core.userdetails.User(userEntity.getEmail(), userEntity.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
