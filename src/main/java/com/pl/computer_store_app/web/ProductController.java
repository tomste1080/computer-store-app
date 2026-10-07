package com.pl.computer_store_app.web;

import com.pl.computer_store_app.category.CategoryService;
import com.pl.computer_store_app.category.dto.CategoryDto;
import com.pl.computer_store_app.product.ProductService;
import com.pl.computer_store_app.product.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetails(@PathVariable Long id, Model model) {
        ProductDto product = productService.findProductById(id);
        CategoryDto category = categoryService.findCategoryById(product.getCategoryId());
        model.addAttribute("product", product);
        model.addAttribute("category", category);
        return "product-details";
    }
}
