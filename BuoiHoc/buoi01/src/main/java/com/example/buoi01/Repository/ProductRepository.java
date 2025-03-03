package com.example.buoi01.Repository;

import com.example.buoi01.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    <T> List<T> findAllBy(Class<T> type);
}
