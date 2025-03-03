package com.example.buoi01.service.impl;

import com.example.buoi01.Repository.ProductRepository;
import com.example.buoi01.domain.Product;
import com.example.buoi01.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ProductImpl implements ProductService {
@Autowired
    ProductRepository productRepository;


    @Override
    public <T> List<T> getAllUser(Class<T> type) {
        return productRepository.findAllBy(type);
    }

    @Override
    public Product saveProduct(Product product) {
     return   productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public <T> Optional<T> getUserById(Long id, Class<T> type) {
        return productRepository.findById(id,type);
    }


    @Override
    public Product updateProduct(Product product, long id) {

        Product updateProduct = productRepository.findById(id).get();
           return productRepository.save(product);



    }


}
