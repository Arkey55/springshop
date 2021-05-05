package ru.romankuznetsov.dto;

import lombok.Data;
import ru.romankuznetsov.entity.OrderItem;
import ru.romankuznetsov.entity.Product;

import java.math.BigDecimal;

@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getTitle();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
