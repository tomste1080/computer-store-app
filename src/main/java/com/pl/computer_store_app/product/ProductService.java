package com.pl.computer_store_app.product;

import com.pl.computer_store_app.product.dto.ProductDto;
import com.pl.computer_store_app.product.dto.ProductFilterCriteriaDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public List<ProductDto> findProductBySlug(String slug) {
         return productRepository.findProductsByCategory_Slug(slug)
                 .stream()
                 .map(ProductDtoMapper::map)
                 .toList();
    }

    public ProductDto findProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ProductDtoMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Nie można znaleźć produktu o takim identyfiaktorze"));
    }

    public List<ProductDto> findProductByFilter(ProductFilterCriteriaDto criteria) {
        return productRepository.findByFilters(
                criteria.getCategory(),
                criteria.getProducer(),
                criteria.getMinPrice(),
                criteria.getMaxPrice()
        )
                .stream()
                .map(ProductDtoMapper::map)
                .toList();
    }

    public List<String> getAllProducers() {
        return productRepository.findDistinctProducers();
    }

    public List<ProductDto> searchProduct(String search) {
        return productRepository.findProductsByNameContainingIgnoreCase(search)
                .stream()
                .map(ProductDtoMapper::map)
                .toList();
    }
}
