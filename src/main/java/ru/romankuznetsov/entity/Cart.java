package ru.romankuznetsov.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@RedisHash("carts")
public class Cart {
    @Id
    private Long ownerId;

    private List<CartItem> cartItems;
    private BigDecimal price;
    public Cart(Long ownerId){
        this.ownerId = ownerId;
    }

    public Cart(){
        this.cartItems = new ArrayList<>();
    }
//    @Id
//    @GenericGenerator(name = "UUIDGenerator", strategy ="uuid2")
//    @GeneratedValue(generator = "UUIDGenerator")
//    @Column(name = "id")
//    private UUID id;
//
//    @OneToMany(mappedBy = "cart")
//    private List<CartItem> cartItems;
//
//    @OneToOne
//    @JoinColumn(name = "owner_id")
//    private User owner;
//
//    @Column(name = "price")
//    private BigDecimal price;
//
//    public void add(CartItem cartItem){
//        for (CartItem item : cartItems) {
//            if (item.getProduct().getId().equals(cartItem.getProduct().getId())){
//                item.increment(cartItem.getQuantity());
//                calculate();
//                return;
//            }
//        }
//        cartItems.add(cartItem);
//        cartItem.setCart(this);
//        calculate();
//    }
//
//    public void calculate(){
//        price = new BigDecimal(0.0);
//        for(CartItem cartItem : cartItems){
//            price.add(cartItem.getPrice());
//        }
//    }
//
//    public void clear(){
//        cartItems.clear();
//        calculate();
//    }
}
