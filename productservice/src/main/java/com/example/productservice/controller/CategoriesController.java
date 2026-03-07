package com.example.productservice.controller;


import com.example.productservice.api.CategoriesApi;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriesController implements CategoriesApi {

    @Override
    public String getCategories(){
        return "I am returning via String";
    }
}
