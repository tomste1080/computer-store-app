package com.pl.computer_store_app.cart;

import com.pl.computer_store_app.cart.dto.CartDto;
import com.pl.computer_store_app.product.ProductDtoMapper;
import com.pl.computer_store_app.user.UserDtoMapper;

public class CartDtoMapper {
    public static CartDto map(Cart cart) {
        return new CartDto(
                cart.getId(),
                cart.getQuantity(),
                cart.getCreatedAt(),
                UserDtoMapper.map(cart.getUser()),
                ProductDtoMapper.map(cart.getProduct())
        );
    }
}
