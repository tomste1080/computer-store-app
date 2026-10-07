package com.pl.computer_store_app.cart.dto;

import com.pl.computer_store_app.product.Product;
import com.pl.computer_store_app.product.dto.ProductDto;
import com.pl.computer_store_app.user.User;
import com.pl.computer_store_app.user.dto.UserDto;

import java.time.LocalDateTime;

public class CartDto {
    private Long id;
    private int quantity;
    private LocalDateTime createdAt;
    private UserDto user;
    private ProductDto product;

    public CartDto() {
    }

    public CartDto(Long id, int quantity, LocalDateTime createdAt, UserDto user, ProductDto product) {
        this.id = id;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.user = user;
        this.product = product;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }
}
