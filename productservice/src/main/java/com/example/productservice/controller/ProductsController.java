package com.example.productservice.controller;

import com.example.productservice.api.ProductsApi;
import com.example.productservice.entity.Products;
import com.example.productservice.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

// TODO: Add Pagination for products only
// TODO: Add Validation and productservice is to be done
// TODO: check if any config is needed as well DTO

@RestController
public class ProductsController implements ProductsApi {

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public Page<Products> getProducts(int page, int size){
        return productService.getAllProducts( page, size);
    }

    @Override
    public Products getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @Override
    public Products saveProduct(@Valid @RequestBody Products products){
        return  productService.saveProduct(products);
    }

    @Override
    public Products updateProductPut(@RequestBody Products products){
        return productService.updateProductsPut(products);
    }

    @Override
    public Products updateProductPatch(@RequestBody Products products){
        return productService.updateProductsPatch(products);
    }
}
