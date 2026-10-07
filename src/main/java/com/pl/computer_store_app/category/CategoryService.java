package com.pl.computer_store_app.category;

import com.pl.computer_store_app.category.dto.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryDto> findAllCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryDtoMapper::map)
                .toList();
    }

    public CategoryDto findCategoryById(Long id) {
        return categoryRepository.findCategoryById(id)
                .map(CategoryDtoMapper::map)
                .orElseThrow(() -> new IllegalArgumentException("Nie znaleziono kateogri o danym idetyfikatorze"));
    }
}
