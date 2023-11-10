package com.auth.services;

import com.auth.dto.product.ProductRequestDTO;
import com.auth.dto.product.ProductResponseDTO;
import com.auth.entities.Product;
import com.auth.exceptions.product.ProductNotFoundException;
import com.auth.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public List<ProductResponseDTO> getProducts() {
        List<ProductResponseDTO> products = this.repository
                .findAll()
                .stream()
                .map(ProductResponseDTO::new).toList();

        if (products.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return products;
    }

    public Product getProductByName(String name) {
        Product product = this.repository.findByName(name);

        if (product == null) {
            throw new ProductNotFoundException();
        }

        return product;
    }

    public Product addProduct(ProductRequestDTO body) {
        Product product = new Product(body);
        this.repository.save(product);

        return product;
    }

    public Product updateProduct(@RequestBody Product product) {
        this.repository.save(product);

        return product;
    }

    public String deleteProducts() {
        List<ProductResponseDTO> allProducts = this.repository.findAll()
                .stream()
                .map(ProductResponseDTO::new).toList();

        this.repository.deleteAll();

        return "All products have been deleted\n" + allProducts.toString();
    }

    public Optional<Product> deleteById(String id) {
        Optional<Product> product = this.repository.findById(id);
        this.repository.deleteById(id);

        return product;
    }
}
