package com.example.productservice.service;

import com.example.productservice.entity.Products;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Products> getAllProducts();

    Products getProductById(Long id);

    Products saveProduct(Products products);

    Products updateProductsPut(Products products);

    Products updateProductsPatch(Products products);

}
