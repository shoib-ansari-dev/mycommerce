package com.example.productservice.service;

import com.example.productservice.dto.ProductFilter;
import com.example.productservice.entity.Products;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Products> getAllProducts(ProductFilter productFilter);

    Products getProductById(Long id);

    Products saveProduct(Products products);

    Products updateProductsPut(Products products);

    Products updateProductsPatch(Products products);

}
