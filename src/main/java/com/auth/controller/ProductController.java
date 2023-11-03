package com.auth.controller;

import com.auth.domain.Product.Product;
import com.auth.domain.Product.ProductRequestDTO;
import com.auth.domain.Product.ProductResponseDTO;
import com.auth.domain.User.User;
import com.auth.repository.ProductRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/store")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> productList = this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new).toList();

        return ResponseEntity.ok(productList);
    }

    @GetMapping("/products/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable(value = "name") String name) {
        Product product = this.repository.findByName(name);

        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody @Valid ProductRequestDTO body) {
        Product product = new Product(body);

        this.repository.save(product);

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        this.repository.save(product);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deleteProducts() {
        List<ProductResponseDTO> allProducts = this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new).toList();

        this.repository.deleteAll();

        return ResponseEntity.ok("All products have been deleted\n" + allProducts);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Optional<Product>> deleteById(@PathVariable(value = "id") String id) {
        Optional<Product> product = this.repository.findById(id);

        this.repository.deleteById(id);

        return ResponseEntity.ok(product);
    }
}
