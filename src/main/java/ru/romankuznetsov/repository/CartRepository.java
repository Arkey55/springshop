package ru.romankuznetsov.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.romankuznetsov.entity.Cart;


@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {

}
