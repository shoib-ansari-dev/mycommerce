package com.example.productservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rating")
public class Ratings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "variant_id", nullable = false, referencedColumnName = "id")
    private Variants variants;

    @Column(name = "user_id",nullable = false)
    private Integer userId;

    @Column(name = "rating_value",nullable = false)
    private Integer ratingValue;

    @Column(name = "review_text")
    private String reviewText;
}
