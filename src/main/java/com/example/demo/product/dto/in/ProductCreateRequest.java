package com.example.demo.product.dto.in;

import java.math.BigDecimal;

public record ProductCreateRequest(
        String sellerId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        String creatorId
) {
}

//public record ProductCreateRequest(
//        String sellerId,
//        String name,
//        String description,
//        BigDecimal price,
//        Integer stock,
//        String status,
//        String creatorId
//) {
//}
