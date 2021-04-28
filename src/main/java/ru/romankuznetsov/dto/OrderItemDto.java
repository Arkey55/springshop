package ru.romankuznetsov.dto;

import lombok.Data;
import ru.romankuznetsov.entity.OrderItem;
import ru.romankuznetsov.entity.Product;

@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private Integer pricePerProduct;
    private Integer price;
    private Integer quantity;

    public OrderItemDto(OrderItem orderItem) {
        this.productId = orderItem.getProduct().getId();
        this.productTitle = orderItem.getTitle();
        this.pricePerProduct = orderItem.getPricePerProduct();
        this.price = orderItem.getPrice();
        this.quantity = orderItem.getQuantity();
    }
}
