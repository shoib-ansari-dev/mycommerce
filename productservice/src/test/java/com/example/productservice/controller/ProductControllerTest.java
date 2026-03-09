package com.example.productservice.controller;

import com.example.productservice.dto.ProductFilterDTO;
import com.example.productservice.entity.Products;
import com.example.productservice.factory.ProductFactory;
import com.example.productservice.factory.ProductFilterDTOFactory;
import com.example.productservice.service.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    ProductsController productsController;

    @Test
    void shouldReturnProducts(){

        Products products= ProductFactory.createProduct();
        Page<Products> productsPage = new PageImpl<>(List.of(products));

        ProductFilterDTO productFilterDTO = ProductFilterDTOFactory.createFilter();

        when(productService.getAllProducts(productFilterDTO)).thenReturn(productsPage);

        ResponseEntity<Page<Products>> response= productsController.getProducts(productFilterDTO);
        assertEquals(200, response.getStatusCode().value());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getSize());
    }

}
