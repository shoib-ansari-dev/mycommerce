package com.example.productservice.repository;

import com.example.productservice.entity.Ratings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Ratings, Long> {
}
