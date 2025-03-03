package com.example.buoi01.service;

import com.example.buoi01.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    <T> List<T> getAllUser( Class<T> type);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    <T>Optional<T> getUserById(Long id,Class<T> type);

   Product updateProduct(Product product ,long id);
}
