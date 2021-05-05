package ru.romankuznetsov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romankuznetsov.entity.Cart;
import ru.romankuznetsov.entity.CartItem;
import ru.romankuznetsov.repository.CartItemRepository;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemService {
    private CartItemRepository cartItemRepository;
    private ProductService productService;

    public void addToCart(Cart cart, Long productId){
        CartItem cartItem = new CartItem(productService.findProductById(productId).orElseThrow());
        cartItemRepository.save(cartItem);
    }

    public Optional<CartItem> findById(Long id){
        return cartItemRepository.findById(id);
    }

    public CartItem saveOrUpdate(CartItem item){
        return cartItemRepository.save(item);
    }
}
