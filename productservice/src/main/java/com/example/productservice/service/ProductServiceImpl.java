package com.example.productservice.service;

import com.example.productservice.dto.ProductFilterDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.exception.ResourceNotFoundException;
import com.example.productservice.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public Page<Products> getAllProducts(ProductFilterDTO productFilterDTO) {
        Sort sort= productFilterDTO.getSortBy().equalsIgnoreCase("ASC")?
                Sort.by(productFilterDTO.getSortBy()).ascending():
                Sort.by(productFilterDTO.getSortBy()).descending();

        Pageable pageable= PageRequest.of(productFilterDTO.getPage(), productFilterDTO.getSize(), sort);

        Specification<Products> productsSpecification = (_, _, _) -> null;

        if (productFilterDTO.getKeyword() != null && !productFilterDTO.getKeyword().isEmpty()) {
            productsSpecification = productsSpecification.and((root, _, cb) ->
                    cb.like(cb.lower(root.get("name")), "%" + productFilterDTO.getKeyword().toLowerCase() + "%"));
        }
        if (productFilterDTO.getCategoryId() != null) {
            productsSpecification = productsSpecification.and((root, _, cb) ->
                    cb.equal(root.get("categoryId"), productFilterDTO.getCategoryId()));
        }

        if (productFilterDTO.getMinPrice() != null) {
            productsSpecification = productsSpecification.and((root, _, cb) ->
                    cb.ge(root.get("price"), productFilterDTO.getMinPrice()));
        }

        if (productFilterDTO.getMaxPrice() != null) {
            productsSpecification = productsSpecification.and((root, _, cb) ->
                    cb.le(root.get("price"), productFilterDTO.getMaxPrice()));
        }

        if (productFilterDTO.getBrand() != null && !productFilterDTO.getBrand().isEmpty()) {
            productsSpecification = productsSpecification.and((root, _, cb) ->
                    cb.like(cb.lower(root.get("brand")), "%" + productFilterDTO.getBrand().toLowerCase() + "%"));
        }

        return productRepository.findAll( productsSpecification,pageable);
    }

    @Override
    public Products getProductById(Long id) {
        Optional<Products> searchedProduct= productRepository.findById(id);
        if(searchedProduct.isEmpty()){
            throw new ResourceNotFoundException("Product not found with id: " + id);
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
