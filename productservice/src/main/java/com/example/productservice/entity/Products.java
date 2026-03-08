package com.example.productservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product name is required")
    @Size(max = 100, message = "Product name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @PositiveOrZero(message = "Weight must be zero or positive")
    @Column(precision = 5, scale = 2)
    private BigDecimal weight;

    @PositiveOrZero(message = "Length must be zero or positive")
    @Column(name = "length_cm", precision = 5, scale = 2)
    private BigDecimal length;

    @PositiveOrZero(message = "Width must be zero or positive")
    @Column(name = "width_cm", precision = 5, scale = 2)
    private BigDecimal width;

    @PositiveOrZero(message = "Height must be zero or positive")
    @Column(name = "height_cm", precision = 5, scale = 2)
    private BigDecimal height;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal price;

    @NotNull(message = "Supplier is required")
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @NotNull(message = "Category is required")
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Categories categories;

    @NotNull(message = "Brand is required")
    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private Brands brands;
}