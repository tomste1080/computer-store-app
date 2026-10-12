package com.pl.computer_store_app.product;

import com.pl.computer_store_app.category.CategoryDtoMapper;
import com.pl.computer_store_app.product.dto.ProductDto;

public class ProductDtoMapper {
    public static ProductDto map(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getSku(),
                product.getProducer(),
                product.getPrice(),
                product.getQuantity(),
                product.getDescription(),
                product.getImageUrl(),
                product.isActive(),
                CategoryDtoMapper.map(product.getCategory())
        );
    }
}
