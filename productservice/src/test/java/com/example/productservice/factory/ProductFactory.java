package com.example.productservice.factory;

import com.example.productservice.entity.*;

import java.math.BigDecimal;

public class ProductFactory {

    public static Products createProduct() {

        Supplier supplier = new Supplier();
        supplier.setId(1L);

        Categories category = new Categories();
        category.setId(1L);

        Brands brand = new Brands();
        brand.setId(1L);

        Products product = new Products();
        product.setId(1L);
        product.setName("Laptop");
        product.setPrice(new BigDecimal("1500"));
        product.setSupplier(supplier);
        product.setCategories(category);
        product.setBrands(brand);

        return product;
    }
}