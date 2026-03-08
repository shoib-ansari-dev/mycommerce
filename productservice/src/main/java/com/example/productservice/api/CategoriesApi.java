package com.example.productservice.api;

import com.example.productservice.entity.Categories;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("v1/categories")
public interface CategoriesApi{

    @GetMapping
    ResponseEntity<List<Categories>> getCategories();
}
