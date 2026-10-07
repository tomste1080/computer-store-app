package com.pl.computer_store_app.web;

import com.pl.computer_store_app.category.CategoryService;
import com.pl.computer_store_app.category.dto.CategoryDto;
import com.pl.computer_store_app.product.Product;
import com.pl.computer_store_app.product.ProductService;
import com.pl.computer_store_app.product.dto.ProductDto;
import com.pl.computer_store_app.product.dto.ProductFilterCriteriaDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public HomeController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(ProductFilterCriteriaDto criteria,
                       @RequestParam(required = false) String search,
                       Model model) {
        if (search == null || search.isBlank()) {
            List<ProductDto> products = productService.findProductByFilter(criteria);
            model.addAttribute("products", products);
        } else {
            List<ProductDto> products = productService.searchProduct(search);
            model.addAttribute("products", products);
        }
        List<CategoryDto> categories = categoryService.findAllCategories();
        List<String> producers = productService.getAllProducers();
        model.addAttribute("categories", categories);
        model.addAttribute("producers", producers);
        return "index";
    }

    @GetMapping("/kategoria/{slug}")
    String getCategory(@PathVariable String slug, Model model) {
        List<ProductDto> products = productService.findProductBySlug(slug);
        model.addAttribute("products", products);
        model.addAttribute("currentCategory", slug);
        return "index";
    }
}
