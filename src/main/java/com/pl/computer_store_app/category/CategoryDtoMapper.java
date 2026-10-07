package com.pl.computer_store_app.category;

import com.pl.computer_store_app.category.dto.CategoryDto;

public class CategoryDtoMapper {
    public static CategoryDto map(Category category) {
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getSlug(),
                category.getDescription()
        );
    }
}
