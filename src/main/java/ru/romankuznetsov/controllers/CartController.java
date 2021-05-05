package ru.romankuznetsov.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ru.romankuznetsov.entity.Cart;
import ru.romankuznetsov.entity.CartItem;
import ru.romankuznetsov.entity.Product;
import ru.romankuznetsov.entity.User;
import ru.romankuznetsov.service.CartService;
import ru.romankuznetsov.service.ProductService;
import ru.romankuznetsov.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@AllArgsConstructor
public class CartController {
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    public Cart getCurrentCart(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.findCartByOwnerId(user.getId());
    }

    @PostMapping
    public Cart updateCart(@RequestBody Cart cart){
        return cartService.updateCart(cart);
    }

    @DeleteMapping
    public Cart clearCart(Principal principal){
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return cartService.clearCart(user.getId());
    }

    @GetMapping("/mock")
    public Cart getMockCart(Principal principal) {
//        if (principal == null) {
//            return cartService.getCartForUser(null, null);
//        }
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        Cart cart = new Cart();
        List<CartItem> items = new ArrayList<>();
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            items.add(new CartItem(product));
        }
        cart.setOwnerId(1L);
        cart.setCartItems(items);

        return cart;
    }
}
