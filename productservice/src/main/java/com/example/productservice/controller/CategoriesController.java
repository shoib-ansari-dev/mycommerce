package com.example.productservice.controller;


import com.example.productservice.api.CategoriesApi;
import com.example.productservice.entity.Categories;
import com.example.productservice.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoriesController implements CategoriesApi {

    @Autowired
    CategoryService categoryService;

    @Override
    public ResponseEntity<List<Categories>> getCategories(){
        List<Categories> categories = categoryService.getCategories();
        if(categories.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(categories);
    }
}
