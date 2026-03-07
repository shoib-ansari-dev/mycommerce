package com.example.productservice.service;

import com.example.productservice.entity.Categories;
import com.example.productservice.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Categories> getCategories() {
        return categoryRepository.findAll();
    }
}
