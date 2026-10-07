package com.pl.computer_store_app.order;

import com.pl.computer_store_app.order.dto.OrderDto;

public class OrderDtoMapper {
    public static OrderDto map(Order order) {
        return new OrderDto(
                order.getId(),
                order.getOrderNumber(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCreatedAt(),
                order.getUser().getId()
        );
    }
}
