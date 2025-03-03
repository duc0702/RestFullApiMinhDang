package com.example.buoi01.service;

import com.example.buoi01.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
 <T> List<T> getAllUser( Class<T> type);
 User saveUser(User user);
 void deleteById(Long id);
 <T>Optional<T> getUserById(Long id,Class<T> type);
 <T>Optional<T> getUserByEmail(String email,Class<T> type);
User updateUser(User user, long id);
}
