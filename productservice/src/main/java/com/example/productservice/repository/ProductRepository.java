package com.example.productservice.repository;

import com.example.productservice.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> getAllProducts();

    Optional<Products> getProductById(Long id);

    Products saveProduct(Products products);

    void deleteById(Long id);
}
