package com.example.productservice.api;

import com.example.productservice.entity.Categories;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name="Categories", description = "Endpoing for managing Categories" )
@RequestMapping("v1/categories")
public interface CategoriesApi{

    @GetMapping
    @Operation(summary = "Get List of Categories", description = "Get a list of Categories from the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of categories received successfully"),
            @ApiResponse(responseCode = "404", description = "No Categories Found")
    })
    ResponseEntity<List<Categories>> getCategories();
}
