package ru.romankuznetsov.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.romankuznetsov.dto.ProductDto;
import ru.romankuznetsov.entity.Product;
import ru.romankuznetsov.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id){
        return productService.findProductDtoById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }

    @PostMapping
    public Product addNewProduct(@RequestBody Product product){
        product.setId(null);
        return productService.save(product);
    }

    @PutMapping
    public Product updateProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productService.deleteById(id);
    }
}
