package ru.romankuznetsov.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.ws.products.ProductSoap;
import ru.romankuznetsov.entity.Product;
import ru.romankuznetsov.repository.ProductRepositorySoap;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceSoap {
    private final ProductRepositorySoap productRepository;
    private static final Function<Product, ProductSoap> functionEntityToSoap = se -> {
        ProductSoap product = new ProductSoap();
        product.setId(se.getId());
        product.setTitle(se.getTitle());
        product.setPrice(se.getPrice());
        return product;
    };

    public List<ProductSoap> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ru.geekbrains.spring.ws.products.ProductSoap getProductById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }
}
