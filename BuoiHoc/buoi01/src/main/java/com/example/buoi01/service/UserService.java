package com.example.buoi01.service;

import com.example.buoi01.domain.User;
import com.example.buoi01.domain.dto.UserDto;
import com.example.buoi01.service.utils.error.InvalidEmailException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
 <T> Set<T> getAllUser( Class<T> type);
 User saveUser(User user) throws InvalidEmailException;
 void deleteById(Long id);
 <T>Optional<T> getUserById(Long id,Class<T> type);
 <T>Optional<T> getUserByEmail(String email);

User updateUser(User user, long id);
void updateRefreshToken( String email,String refreshToken);
Optional<User> getUserByRefreshTokenAndEmail( String email,String refreshToken);

 Page<UserDto> findAllByWithPageable(Pageable pageable);

 List<User> searchByName (String name);
 
 
}
