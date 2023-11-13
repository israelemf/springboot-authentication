package com.auth.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class ProductRequestDTO {
    @NotBlank(message = "Product name is required!")
    private String name;
    private String description;
    @NotNull(message = "Product stock is required!")
    private Integer quantity;
    @NotNull(message = "Product price is required!")
    private BigDecimal price;

    public ProductRequestDTO() {
    }

    public ProductRequestDTO(String name, String description, Integer quantity, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
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
}
