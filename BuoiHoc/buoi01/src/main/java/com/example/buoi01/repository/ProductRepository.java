package com.example.buoi01.repository;

import com.example.buoi01.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>,JpaSpecificationExecutor<Product> {
    <T> List<T> findAllBy(Class<T> type);
    <T> Optional<T> findById(Long id, Class<T> type);

   Page  <Product> findAllBy(Pageable pageable);

   
}
