package com.auth.dto.product;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ProductRequestDTO {
    @NotBlank(message = "Product name is required!")
    private String name;
    @NotBlank(message = "Product price is required!")
    private BigDecimal price;

    public ProductRequestDTO(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "ProductRequestDTO{" +
                "name='" + name + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
