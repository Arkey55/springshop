package ru.romankuznetsov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romankuznetsov.entity.Cart;
import ru.romankuznetsov.entity.CartItem;
import ru.romankuznetsov.repository.CartRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CartService {

    private CartRepository cartRepository;

    public Cart updateCart(Cart cart){
        recalculateCart(cart);
        return cartRepository.save(cart);
    }

    public Cart findCartByOwnerId(Long id){
        Cart cart = cartRepository.findById(id).orElse(new Cart(id));
        return cart;
    }

    public Cart clearCart(Long id){
        Cart cart = findCartByOwnerId(id);
        cart.getCartItems().clear();
        return cartRepository.save(cart);
    }

    private void recalculateCart(Cart cart){
        cart.setPrice(new BigDecimal("0.0"));
        for (CartItem cartItem : cart.getCartItems()){
            cart.setPrice(cart.getPrice().add(cartItem.getPrice()));
        }
    }
}
