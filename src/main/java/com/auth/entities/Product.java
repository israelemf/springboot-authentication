package com.auth.entities;

import com.auth.dto.product.ProductRequestDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "products")
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 10, nullable = false, precision = 2)
    private BigDecimal price;

    public Product() {
    }

    public Product(String id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product(ProductRequestDTO data) {
        this.price = data.getPrice();
        this.name = data.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
