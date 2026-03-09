package com.example.productservice.factory;

import com.example.productservice.dto.ProductFilterDTO;

import java.math.BigDecimal;

public class ProductFilterDTOFactory {

    public static ProductFilterDTO createFilter() {
        ProductFilterDTO filter = new ProductFilterDTO();

        filter.setKeyword("laptop");
        filter.setCategoryId(1L);
        filter.setMinPrice(new BigDecimal("500"));
        filter.setMaxPrice(new BigDecimal("2000"));
        filter.setBrand("Dell");
        filter.setPage(0);
        filter.setSize(10);
        filter.setSortBy("name");
        filter.setSortDir("asc");

        return filter;
    }
}