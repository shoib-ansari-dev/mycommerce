package com.example.productservice.service;

import com.example.productservice.entity.Products;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Page<Products> getAllProducts(int page, int size);

    Products getProductById(Long id);

    Products saveProduct(Products products);

    Products updateProductsPut(Products products);

    Products updateProductsPatch(Products products);

}
