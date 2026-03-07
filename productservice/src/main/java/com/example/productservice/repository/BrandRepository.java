package com.example.productservice.repository;

import com.example.productservice.entity.Brands;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brands, Long> {
}
