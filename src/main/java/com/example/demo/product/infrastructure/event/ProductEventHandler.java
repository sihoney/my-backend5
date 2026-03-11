package com.example.demo.product.infrastructure.event;

import com.example.demo.product.application.event.ProductCreatedEvent;
import com.example.demo.product.application.event.ProductDeletedEvent;
import com.example.demo.product.application.event.ProductUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ProductEventHandler {

    private static final Logger log = LoggerFactory.getLogger(ProductEventHandler.class);

//    비동기 작동 시(AsyncConfig 등록 필수)
    @Async
    @EventListener
//    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void on(ProductCreatedEvent event) {
        log.info("ProductCreatedEvent: productId={}, actorId={}", event.productId(), event.actorId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductCreatedEvent event) {
        log.info("ProductCreatedEvent: productId={}, actorId={}", event.productId(), event.actorId());

//        ProductCreatedEvent: productId=4f173b0a-278d-4d5f-87e2-eb36133129ac, actorId=11111111-1111-1111-1111-111111111111
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductUpdatedEvent event) {
        log.info("ProductUpdatedEvent: productId={}, actorId={}", event.productId(), event.actorId());
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handle(ProductDeletedEvent event) {
        log.info("ProductDeletedEvent: productId={}, actorId={}", event.productId(), event.actorId());
    }
}
