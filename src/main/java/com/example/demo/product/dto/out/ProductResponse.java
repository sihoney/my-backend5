package com.example.demo.product.dto.out;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "")
public record ProductResponse(
        UUID id,
        UUID sellrId,
        String name,
        String description,
        BigDecimal price,
        Integer stock,
        String status,
        LocalDateTime modifyDt
) {
}
