package ru.romankuznetsov.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import ru.romankuznetsov.entity.User;
import ru.romankuznetsov.entity.Product;
import ru.romankuznetsov.repository.UserRepository;
import ru.romankuznetsov.repository.ProductRepository;
import ru.romankuznetsov.service.Cart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@SessionScope
@RequestMapping("/api/v1/cart")
public class CartController {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final Cart cart;

    public CartController(UserRepository userRepository, ProductRepository productRepository, Cart cart) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.cart = cart;
    }

    @GetMapping
    public Map<User, List<Product>> showAll(){
        return cart.getCart();
    }

    @GetMapping("/{id}")
    public List<Product> showById(@PathVariable long id){
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
        return cart.getCart().get(user);
    }

    @PostMapping("{userId}/{productId}")
    public void addToCart(@PathVariable long userId, @PathVariable long productId){
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("user not found"));
        List<Product> productList = new ArrayList<>();
        productList.add(productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Product not found")));
        for (Product p : productList) {
            cart.getCart().computeIfAbsent(user, k -> new ArrayList<>()).add(p);
        }
    }
}
