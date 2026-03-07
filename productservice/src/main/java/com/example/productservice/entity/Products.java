package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private String description;

    @Column(precision = 5, scale = 2)
    private Double weight;

    @Column(name = "length_cm", precision = 5, scale = 2)
    private Double length;

    @Column(name = "width_cm", precision = 5, scale = 2)
    private Double width;

    @Column(name = "height_cm", precision = 5, scale = 2)
    private Double height;

    @Column(nullable = false, precision = 5, scale = 2)
    private Double price;

    @OneToMany
    @JoinColumn(name = "supplier_id", nullable = false, referencedColumnName = "id")
    private Supplier supplier;

    @OneToMany
    @JoinColumn(name = "category_id", nullable = false, referencedColumnName = "id")
    private Categories categories;

    @OneToMany
    @JoinColumn(name = "brand_id", nullable = false, referencedColumnName = "id")
    private Brands brands;



}
