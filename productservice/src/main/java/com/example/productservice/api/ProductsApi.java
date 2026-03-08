package com.example.productservice.api;

import com.example.productservice.entity.Products;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/products")
public interface ProductsApi {
    @GetMapping
    Page<Products> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    );

    @GetMapping("/{id}")
    Products getProductById(@PathVariable Long id);

    @PostMapping
    Products saveProduct(@RequestBody Products products);

    @PutMapping
    Products updateProductPut(@RequestBody Products products);

    @PatchMapping
    Products updateProductPatch(@RequestBody Products products);
}