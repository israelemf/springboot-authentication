package com.auth.repository;

import com.auth.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
    Product findByName(String name);
}
