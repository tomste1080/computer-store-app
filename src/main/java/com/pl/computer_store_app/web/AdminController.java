package com.pl.computer_store_app.web;

import com.pl.computer_store_app.category.Category;
import com.pl.computer_store_app.category.CategoryService;
import com.pl.computer_store_app.category.dto.CategoryDto;
import com.pl.computer_store_app.product.ProductService;
import com.pl.computer_store_app.product.dto.ProductDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/admin/produkty/usun")
    public String deleteProduct(@RequestParam Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @GetMapping("/admin/produkty/dodaj")
    public String getAddProductForm(Model model) {
        ProductDto productDto = new ProductDto();
        productDto.setCategoryDto(new CategoryDto());
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("product", productDto);
        model.addAttribute("categories", categories);
        return "product-add";
    }

    @PostMapping("/admin/produkty/dodaj")
    public String addProduct(
            @ModelAttribute("product") ProductDto productDto,
            Model model
    ) {
        productService.addProduct(productDto);
        model.addAttribute("success", "Udało się dodac nowy produkt");
        return "redirect:/";
    }
}
