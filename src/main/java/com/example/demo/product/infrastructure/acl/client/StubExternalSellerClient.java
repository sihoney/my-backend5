package com.example.demo.product.infrastructure.acl.client;


import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

/**
 * 외부 클라이언트(현재는 스텁)
 */

@Component
public class StubExternalSellerClient implements ExternalSellerClient {

    @Override
    public Optional<ExternalSellerPayload> findSeller(UUID sellerId) {
        // Local stub for ACL integration; replace with real HTTP/RPC adapter later.
        return Optional.of(new ExternalSellerPayload(sellerId.toString(), "ACTIVE"));
    }
}

