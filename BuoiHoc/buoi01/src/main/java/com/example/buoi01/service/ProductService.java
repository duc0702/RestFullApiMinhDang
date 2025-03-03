package com.example.buoi01.service;

import com.example.buoi01.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> getAllProduct();
    Product saveProduct(Product product);
    void deleteProduct(Long id);
   Product getOneProduct(Long id);

   Product updateProduct(Product product ,long id);
}
