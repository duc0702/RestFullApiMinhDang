package com.example.buoi01.service.impl;

import com.example.buoi01.repository.ProductRepository;
import com.example.buoi01.domain.Product;
import com.example.buoi01.domain.dto.res.FillterProductDTO;
import com.example.buoi01.domain.specs.ProductSpecs;
import com.example.buoi01.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public <T> Optional<T> getUserById(Long id, Class<T> type) {
        return productRepository.findById(id, type);
    }

    @Override
    public Product updateProduct(Product product, long id) {

        Product updateProduct = productRepository.findById(id).get();
        return productRepository.save(product);

    }

    @Override
    public Page<Product> findAllByWithPageable(int page, int size, String sortBy, String sortType) {
        Sort sort = sortType.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAllBy(pageable);

    }

    @Override
    public List<Product> searchByBetweenPrice(int maxPrice, int minPrice) {
       Specification<Product> specification = (root, query, criteriaBuilder) -> {
           return criteriaBuilder.between(root.get("price"),minPrice,maxPrice);
       };
        return productRepository.findAll(specification);
    }

    @Override
    public Page<Product> findAllByNameBetweenPriceAndPage( Pageable pageable, FillterProductDTO fillterProductDTO) {
       Specification<Product> specification= Specification.where(null);
       if (fillterProductDTO.getName() != null && !fillterProductDTO.getName().isEmpty()) {
        specification = specification.and(ProductSpecs.nameLike(fillterProductDTO.getName()));
    }
    try {
        if (fillterProductDTO.getPriceMin() != null && !fillterProductDTO.getPriceMin().isEmpty() &&
            fillterProductDTO.getPriceMax() != null && !fillterProductDTO.getPriceMax().isEmpty()) {

            int priceMin = Integer.parseInt(fillterProductDTO.getPriceMin());
            int priceMax = Integer.parseInt(fillterProductDTO.getPriceMax());

            // Kiểm tra logic hợp lệ
            if (priceMin >= 0 && priceMax >= 0 && priceMin <= priceMax) {
                specification = specification.and(ProductSpecs.priceBetween(priceMin, priceMax));
            } else {
                throw new IllegalArgumentException("Khoảng giá không hợp lệ");
            }
        }
    } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Giá phải là số hợp lệ");
    }
       return productRepository.findAll(specification,pageable);
    }

}
