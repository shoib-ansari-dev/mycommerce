package com.example.productservice.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductFilterDTO {

    @Size(max = 100, message = "Search Keyword must not exceed 100 character limit")
    @Pattern(
            regexp = "^[a-zA-Z0-9\\s\\-_.]*$",
            message = "Keyword contains invalid characters"
    )
    private String keyword;

    @Positive(message = "CategoryId must be positive")
    private Long categoryId;

    @Positive(message = "MinPrice must be positive")
    private BigDecimal minPrice;

    @Positive(message = "MaxPrice must be positive")
    private BigDecimal maxPrice;

    @Pattern(
            regexp = "^[a-zA-Z0-9\\s-]{0,50}$",
            message = "Brand can contain letters, numbers, spaces, and hyphens only"
    )
    private String brand;

    @Min(value = 0, message = "Page must be greater than or equal to 0")
    private Integer page = 0;

    @Min(value = 1, message = "Size must be at least 1")
    @Max(value = 100, message = "Size cannot exceed 100")
    private Integer size = 10;

    @Pattern(
            regexp = "^(name|price|createdAt|brand)$",
            message = "SortBy must be one of: name, price, createdAt, brand"
    )
    private String sortBy = "name";

    @Pattern(
            regexp = "^(asc|desc)$",
            message = "SortDir must be either 'asc' or 'desc'"
    )
    private String sortDir = "asc";

}
