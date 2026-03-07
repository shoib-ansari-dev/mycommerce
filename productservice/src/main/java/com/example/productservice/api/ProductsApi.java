package com.example.productservice.api;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/products")
public interface ProductsApi {
    @GetMapping
    String getProducts();

    @GetMapping("/{id}")
    String getProductById(@PathVariable String id);

    @PostMapping
    String saveProduct(@RequestBody String str);

    @PutMapping
    String updateProductPut(@RequestBody String str);

    @PatchMapping
    String updateProductPatch(@RequestBody String str);
}