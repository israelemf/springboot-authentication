package com.auth.exceptions.product;

import com.auth.exceptions.GlobalException;

public class ProductNotFoundException extends GlobalException {
    public ProductNotFoundException() {
        super("Products not found!");
    }
}
