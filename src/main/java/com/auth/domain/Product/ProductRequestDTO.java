package com.auth.domain.Product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public class ProductRequestDTO {
    @NotBlank(message = "Product name is required!")
    private String name;
    @NotBlank(message = "Product price is required!")
    private String price;


}
