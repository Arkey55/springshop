package ru.romankuznetsov.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.romankuznetsov.dto.ProductDto;
import ru.romankuznetsov.entity.Product;
import ru.romankuznetsov.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Optional<Product> findProductById (Long id){
        return productRepository.findById(id);
    }

    public Optional<ProductDto> findProductDtoById(Long id){
        return productRepository.findById(id).map(ProductDto::new);
    }

    public List<ProductDto> findAll(){
        return productRepository.findAll().stream().map(ProductDto::new).collect(Collectors.toList());
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
