package ru.romankuznetsov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romankuznetsov.entity.Order;
import ru.romankuznetsov.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {
    private UserService userService;
    private OrderRepository orderRepository;

    public Optional<Order> findById(Long id){
        return orderRepository.findById(id);
    }

    public List<Order> findAllByOwner(String username){
        return orderRepository.findAllByOwnerUsername(username);
    }
}
