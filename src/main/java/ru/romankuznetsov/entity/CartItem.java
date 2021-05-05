package ru.romankuznetsov.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;

//@Entity
@Data
@NoArgsConstructor
@RedisHash("cart_item")
//@Table(name = "cart_items")
public class CartItem {
    @Id
    private Long id;

    @Indexed
    private Cart cart;

    private Product product;

    private String title;
    private Integer quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public CartItem(Product product) {
        this.product = product;
        this.title = product.getTitle();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = pricePerProduct;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Long id;
//
//    @ManyToOne
//    @JoinColumn(name = "cart_id")
//    private Cart cart;
//
//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//
//    @Column(name = "title")
//    private String title;
//
//    @Column(name = "quantity")
//    private Integer quantity;
//
//    @Column(name = "price_per_product")
//    private BigDecimal pricePerProduct;
//
//    @Column(name = "price")
//    private BigDecimal price;
//
//    @CreationTimestamp
//    @Column(name = "created_at")
//    private LocalDateTime createdAt;
//
//    @UpdateTimestamp
//    @Column(name = "updated_at")
//    private LocalDateTime updatedAt;
//
//
//    public void increment(int quantity){
//        this.quantity += quantity;
//        price = this.pricePerProduct.multiply(new BigDecimal(this.quantity));
//    }
//
//    public void decrement(int quantity){
//        this.quantity -= quantity;
//        price = this.pricePerProduct.multiply(new BigDecimal(this.quantity));
//    }
}
