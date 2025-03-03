package com.example.buoi01.repository;

import com.example.buoi01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    <T>List<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
    <T> Optional<T> findByEmail(String email, Class<T> type);

}
