package com.pl.computer_store_app.product;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findById(Long id);

    @Query("SELECT DISTINCT p.producer FROM Product p WHERE p.producer IS NOT NULL")
    List<String> findDistinctProducers();

    List<Product> findProductsByCategory_Slug(String slug);

    List<Product> findProductsByNameContainingIgnoreCase(String search);

    @Query("""
    SELECT p FROM Product p 
    WHERE (:category IS NULL OR :category= '' OR p.category.slug = :category)
    AND(:producer IS NULL OR :producer= '' OR p.producer = :producer)
    AND(:minPrice IS NULL OR p.price >= :minPrice)
    AND(:maxPrice IS NULL OR p.price <= :maxPrice)
""")
    List<Product> findByFilters(
            @Param("category") String category,
            @Param("producer") String producer,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice
    );
}

