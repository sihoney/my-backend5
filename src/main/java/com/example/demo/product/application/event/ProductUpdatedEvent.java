package com.example.demo.product.application.event;

import java.util.UUID;

public record ProductUpdatedEvent(UUID productId, UUID actorId) {
}

