package com.auth.domain.Product;

import java.math.BigDecimal;

public class ProductResponseDTO {
    private String id;
    private String name;
    private BigDecimal price;

    public ProductResponseDTO(String id, String name, BigDecimal price) {
    }

    public ProductResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
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
