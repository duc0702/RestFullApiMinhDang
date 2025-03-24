package com.example.buoi01.service;

import com.example.buoi01.domain.Product;
import com.example.buoi01.domain.dto.res.FillterProductDTO;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    <T> List<T> getAllUser( Class<T> type);
    Product saveProduct(Product product);
    void deleteProduct(Long id);
    <T>Optional<T> getUserById(Long id,Class<T> type);

   Product updateProduct(Product product ,long id);

   Page<Product> findAllByWithPageable(int page, int size, String sortBy, String sortType);

   List<Product> searchByBetweenPrice (int maxPrice,int minPrice);

    Page<Product> findAllByNameBetweenPriceAndPage(Pageable pageable, FillterProductDTO fillterProductDTO);

}
