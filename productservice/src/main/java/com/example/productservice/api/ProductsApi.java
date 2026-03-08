package com.example.productservice.api;

import com.example.productservice.dto.ProductFilterDTO;
import com.example.productservice.entity.Products;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Products", description = "Endpoints for managing products")
@RequestMapping("v1/products")
public interface ProductsApi {

    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a paginated list of products matching filter criteria")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of products retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid filter criteria")
    })
    ResponseEntity<Page<Products>> getProducts(@Valid ProductFilterDTO productFilterDTO);

    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Returns a single product by its unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ResponseEntity<Products> getProductById(@PathVariable Long id);

    @PostMapping
    @Operation(summary = "Create a new product", description = "Adds a new product to the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product data")
    })
    ResponseEntity<Products> saveProduct(@RequestBody Products products);

    @PutMapping
    @Operation(summary = "Update a product (PUT)", description = "Updates an existing product fully")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ResponseEntity<Products> updateProductPut(@RequestBody Products products);

    @PatchMapping
    @Operation(summary = "Update a product (PATCH)", description = "Updates an existing product partially")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    ResponseEntity<Products> updateProductPatch(@RequestBody Products products);
}