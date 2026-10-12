package com.pl.computer_store_app.product;

import com.pl.computer_store_app.category.Category;
import com.pl.computer_store_app.category.CategoryRepository;
import com.pl.computer_store_app.product.dto.ProductDto;
import com.pl.computer_store_app.product.dto.ProductFilterCriteriaDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    @Transactional
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }

    @Transactional
    public void addProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setSku(productDto.getSku());
        product.setProducer(productDto.getProducer());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setActive(true);
        Category category = categoryRepository.findCategoryById(productDto.getCategoryDto().getId())
                        .orElseThrow(() -> new IllegalArgumentException("Nie można znaleźć kategorii o takim ID"));
        product.setCategory(category);
        productRepository.save(product);
    }
}
