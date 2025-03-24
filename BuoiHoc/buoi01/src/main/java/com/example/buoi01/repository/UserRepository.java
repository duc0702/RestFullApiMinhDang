package com.example.buoi01.repository;

import com.example.buoi01.domain.User;
import com.example.buoi01.domain.dto.UserDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User,Long>,JpaSpecificationExecutor<User> {
    <T>Set<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
    <T> Optional<T> findByEmail(String email);
   
    Optional<User> findByEmailAndRefreshToken(String email, String refreshToken);

    Page<UserDto> findAllBy(Pageable pageable);
}
