package com.example.productservice.service;

import com.example.productservice.dto.ProductFilterDTO;
import com.example.productservice.entity.Products;
import org.springframework.data.domain.Page;

public interface ProductService {
    Page<Products> getAllProducts(ProductFilterDTO productFilterDTO);

    Products getProductById(Long id);

    Products saveProduct(Products products);

    Products updateProductsPut(Products products);

    Products updateProductsPatch(Products products);

}
