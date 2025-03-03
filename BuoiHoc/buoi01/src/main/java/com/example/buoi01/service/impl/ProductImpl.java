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
    public List<Product> getAllProduct() {
        return productRepository.findAll();
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
    public Product getOneProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent())return product.get();

        return null;

    }

    @Override
    public Product updateProduct(Product product, long id) {

        Product updateProduct = productRepository.findById(id).get();
           return productRepository.save(product);



    }


}
