package com.example.productservice.repository;

import com.example.productservice.entity.Variants;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VariantRepository extends JpaRepository<Variants, Long> {
}
