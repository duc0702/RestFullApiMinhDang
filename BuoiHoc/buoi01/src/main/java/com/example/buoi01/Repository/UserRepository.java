package com.example.buoi01.Repository;

import com.example.buoi01.domain.Dto.UserDto;
import com.example.buoi01.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    <T>List<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
}
