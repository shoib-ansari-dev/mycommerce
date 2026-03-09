package com.example.productservice.controller;

import com.example.productservice.entity.Categories;
import com.example.productservice.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CategoriesControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoriesController categoriesController;

    @Test
    void shouldReturnCategories(){
        List<Categories> categoriesList= List.of(new Categories(1L, "Electronics"));

        when(categoryService.getCategories()).thenReturn(categoriesList);

        ResponseEntity<List<Categories>> response= categoriesController.getCategories();
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
    }

    @Test
    void shouldReturnNoContent(){
        List<Categories> categoriesList= List.of();

        when(categoryService.getCategories()).thenReturn(categoriesList);

        ResponseEntity<List<Categories>> response = categoriesController.getCategories();

        assertEquals(204, response.getStatusCode().value());
        assertEquals(0, categoriesList.size());
    }
}