package com.auth.controllers;

import com.auth.entities.Product;
import com.auth.dto.product.ProductRequestDTO;
import com.auth.dto.product.ProductResponseDTO;
import com.auth.repository.ProductRepository;
import com.auth.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store/products")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        return ResponseEntity.ok(this.service.getProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable(value = "name") String name) throws Exception {
        Product product = this.service.getProductByName(name);

        if (product == null) {
            throw new Exception("teste");
        }

        return ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequestDTO body) {
        return ResponseEntity.ok(this.service.addProduct(body));
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        this.service.updateProduct(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducts() {
        return ResponseEntity.ok(this.service.deleteProducts());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Product>> deleteById(@PathVariable(value = "id") String id) {
        return ResponseEntity.ok(this.service.deleteById(id));
    }
}
