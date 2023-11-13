package com.auth.dto.product;

import com.auth.entities.Product;

import java.math.BigDecimal;

public class ProductResponseDTO {
    private String id;
    private String name;
    private String description;
    private Integer quantity;
    private BigDecimal price;

    public ProductResponseDTO(String id, String name, String description, Integer quantity, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getDescription(), product.getQuantity(), product.getPrice());
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
}
