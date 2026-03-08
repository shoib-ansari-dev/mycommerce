package com.example.productservice.controller;

import com.example.productservice.api.ProductsApi;
import com.example.productservice.dto.ProductFilterDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.service.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ProductsController implements ProductsApi {

    private final ProductServiceImpl productService;

    public ProductsController(ProductServiceImpl productService){
        this.productService= productService;
    }

    @Override
    public ResponseEntity<Page<Products>> getProducts(ProductFilterDTO productFilterDTO){
        Page<Products> productsPage = productService.getAllProducts(productFilterDTO);
        if(productsPage.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productsPage);
    }

    @Override
    public ResponseEntity<Products> getProductById(@PathVariable("id") Long id){
        Products productById = productService.getProductById(id);

        if(productById == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productById);
    }

    @Override
    public ResponseEntity<Products> saveProduct(@Valid @RequestBody Products products){
        Products saveProduct = productService.saveProduct(products);

        URI location = URI.create("/products/" + saveProduct.getId());

        if(saveProduct.getId() == null ){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity
                .created(location)
                .body(saveProduct);
    }

    @Override
    public ResponseEntity<Products> updateProductPut(@RequestBody Products products){
        Products updateProductsPut = productService.updateProductsPut(products);

        if(updateProductsPut.getId() == null ){
            return ResponseEntity.noContent().build();
        }
        URI location =URI.create("/products/" + updateProductsPut.getId());

        return ResponseEntity
                .created(location)
                .body(updateProductsPut);
    }

    @Override
    public ResponseEntity<Products> updateProductPatch(@RequestBody Products products){
        Products updateProductsPatch = productService.updateProductsPatch(products);

        if(updateProductsPatch.getId() == null ){
            return ResponseEntity.noContent().build();
        }
        URI location =URI.create("/products/" + updateProductsPatch.getId());

        return ResponseEntity
                .created(location)
                .body(updateProductsPatch);
    }
}
