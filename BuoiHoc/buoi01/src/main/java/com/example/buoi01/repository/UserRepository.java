package com.example.buoi01.repository;

import com.example.buoi01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long> {
    <T>Set<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
    <T> Optional<T> findByEmail(String email);
   
     Optional<User> getUserByRefreshTokenAndEmail( String email,String refreshToken);
}
