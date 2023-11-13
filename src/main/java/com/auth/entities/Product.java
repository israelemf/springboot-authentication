package com.auth.entities;

import com.auth.dto.product.ProductRequestDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Table(name = "products")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(length = 200, nullable = false)
    private String name;
    private String description;
    @Column(nullable = false)
    private Integer quantity;
    @Column(length = 10, nullable = false)
    private BigDecimal price;

    public Product() {
    }

    public Product(ProductRequestDTO data) {
        this.price = data.getPrice();
        this.quantity = data.getQuantity();
        this.name = data.getName();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getQuantity() {
        return quantity;
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
