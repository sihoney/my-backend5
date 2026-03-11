package com.example.demo.product.infrastructure.acl.client;

/**
 * 외부 클라이언트(현재는 스텁)
 */

public record ExternalSellerPayload (
        String sellerNo,
        String sellerStatusCode
){
}
