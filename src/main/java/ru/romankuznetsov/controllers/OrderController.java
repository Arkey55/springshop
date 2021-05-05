package ru.romankuznetsov.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.romankuznetsov.dto.OrderDto;
import ru.romankuznetsov.entity.Order;
import ru.romankuznetsov.entity.User;
import ru.romankuznetsov.service.CartService;
import ru.romankuznetsov.service.OrderService;
import ru.romankuznetsov.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final CartService cartService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrderFromCart(Principal principal, @RequestParam String address) {
        Order order = orderService.createFromUserCart(principal.getName(), address);
        User user = userService.findByUsername(principal.getName()).orElseThrow();
        cartService.clearCart(user.getId());
        return new OrderDto(order);
    }

    @GetMapping("/{id}")
    public OrderDto getOrderById(@PathVariable Long id) {
        Order order = orderService.findById(id).orElseThrow(() -> new RuntimeException("Order not found"));
        return new OrderDto(order);
    }

    @GetMapping
    public List<OrderDto> getCurrentUserOrders(Principal principal) {
        return orderService.findAllByOwner(principal.getName()).stream().map(OrderDto::new).collect(Collectors.toList());
//        return orderService.findAllByOwner(principal.getName());
    }
}
