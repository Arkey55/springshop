package ru.romankuznetsov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.romankuznetsov.entity.Order;
import ru.romankuznetsov.entity.Product;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByOwnerUsername(String ownerUsername);
}
