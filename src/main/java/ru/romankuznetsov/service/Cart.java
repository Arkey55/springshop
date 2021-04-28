package ru.romankuznetsov.service;

import org.springframework.stereotype.Component;
import ru.romankuznetsov.entity.User;
import ru.romankuznetsov.entity.Product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class Cart {
    private Map<User, List<Product>> cart = new HashMap<>();

    public Map<User, List<Product>> getCart() {
        return cart;
    }

    public void setCart(Map<User, List<Product>> cart) {
        this.cart = cart;
    }
}
