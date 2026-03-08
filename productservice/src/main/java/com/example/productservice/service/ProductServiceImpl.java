package com.example.productservice.service;

import com.example.productservice.entity.Products;
import com.example.productservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public Page<Products> getAllProducts(int page, int size, String sortBy, String sortDir) {
        Sort sort= sortDir.equalsIgnoreCase("ASC")? Sort.by(sortBy).ascending(): Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    @Override
    public Products getProductById(Long id) {
        Optional<Products> searchedProduct= productRepository.findById(id);
        if(searchedProduct.isEmpty()){
            throw new RuntimeException("Product not found with id: " + id);
        }
        return searchedProduct.get();
    }

    @Override
    public Products saveProduct(Products products) {
        return productRepository.save(products);
    }

    @Override
    public Products updateProductsPut(Products products) {
        Optional<Products> productsOptional= productRepository.findById(products.getId());

        if (productsOptional.isEmpty()){
            throw new RuntimeException("Product not found with id: " + products.getId());
        }
        productRepository.deleteById(products.getId());
        return productRepository.save(products);
    }

    @Override
    public Products updateProductsPatch(Products products) {
        Optional<Products> productsOptional= productRepository.findById(products.getId());
        if(productsOptional.isEmpty()){
            throw new RuntimeException("Product not found with id: " + products.getId());
        }
        Products existing= productsOptional.get();

        if (products.getName() != null) {
            existing.setName(products.getName());
        }

        if (products.getDescription() != null) {
            existing.setDescription(products.getDescription());
        }

        if (products.getWeight() != null) {
            existing.setWeight(products.getWeight());
        }

        if (products.getLength() != null) {
            existing.setLength(products.getLength());
        }

        if (products.getWidth() != null) {
            existing.setWidth(products.getWidth());
        }

        if (products.getHeight() != null) {
            existing.setHeight(products.getHeight());
        }

        if (products.getPrice() != null) {
            existing.setPrice(products.getPrice());
        }

        if (products.getSupplier() != null) {
            existing.setSupplier(products.getSupplier());
        }

        if (products.getCategories() != null) {
            existing.setCategories(products.getCategories());
        }

        if (products.getBrands() != null) {
            existing.setBrands(products.getBrands());
        }

        return productRepository.save(existing);
    }
}
