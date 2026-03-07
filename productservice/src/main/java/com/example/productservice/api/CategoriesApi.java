package com.example.productservice.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("categories")
public interface CategoriesApi{

    @GetMapping
    String getCategories();
}
