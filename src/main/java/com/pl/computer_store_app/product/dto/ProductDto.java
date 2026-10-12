package com.pl.computer_store_app.product.dto;

import com.pl.computer_store_app.category.Category;
import com.pl.computer_store_app.category.dto.CategoryDto;

import java.math.BigDecimal;

public class ProductDto {
    private Long id;
    private String name;
    private String sku;
    private String producer;
    private BigDecimal price;
    private int quantity;
    private String description;
    private String imageUrl;
    private boolean active;
    private CategoryDto categoryDto;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, String sku, String producer, BigDecimal price, int quantity, String description, String imageUrl, boolean active, CategoryDto categoryDto) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.producer = producer;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.imageUrl = imageUrl;
        this.active = active;
        this.categoryDto = categoryDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }
}
