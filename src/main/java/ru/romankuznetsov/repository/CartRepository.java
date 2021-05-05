package ru.romankuznetsov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.romankuznetsov.entity.Cart;

import java.util.UUID;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
