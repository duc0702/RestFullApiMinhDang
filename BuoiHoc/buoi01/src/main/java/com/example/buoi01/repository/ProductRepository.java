package com.example.buoi01.repository;

import com.example.buoi01.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    <T> List<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);
}
