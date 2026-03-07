package com.example.productservice.controller;

import com.example.productservice.api.ProductsApi;
import com.example.productservice.entity.Products;
import com.example.productservice.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController implements ProductsApi {

    @Autowired
    private ProductServiceImpl productService;

    @Override
    public List<Products> getProducts(){
        return productService.getAllProducts();
    }

    @Override
    public Products getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
    }

    @Override
    public Products saveProduct(@RequestBody Products products){
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
