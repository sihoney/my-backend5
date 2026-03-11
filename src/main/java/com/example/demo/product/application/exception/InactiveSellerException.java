package com.example.demo.product.application.exception;

import java.util.UUID;

public class InactiveSellerException extends RuntimeException {

    public InactiveSellerException(UUID sellerId) {
        super("Seller is inactive. sellerId=" + sellerId);
    }
}
