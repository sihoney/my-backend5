package com.example.demo.product.infrastructure.acl;


import com.example.demo.product.application.acl.SellerAcl;
import com.example.demo.product.application.acl.SellerIdentity;
import com.example.demo.product.application.exception.InactiveSellerException;
import com.example.demo.product.application.exception.SellerNotFoundException;
import com.example.demo.product.infrastructure.acl.client.ExternalSellerClient;
import com.example.demo.product.infrastructure.acl.client.ExternalSellerPayload;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * - ACL 어댑터(번역기)
 */

@Component
public class SellerAclAdapter implements SellerAcl {

    private static final String ACTIVE_STATUS = "ACTIVE";

    private final ExternalSellerClient externalSellerClient;

    public SellerAclAdapter(ExternalSellerClient externalSellerClient) {
        this.externalSellerClient = externalSellerClient;
    }

    @Override
    public SellerIdentity loadActiveSeller(UUID sellerId) {
        ExternalSellerPayload payload = externalSellerClient.findSeller(sellerId)
                .orElseThrow(() -> new SellerNotFoundException(sellerId));

        if (!ACTIVE_STATUS.equalsIgnoreCase(payload.sellerStatusCode())) {
            throw new InactiveSellerException(sellerId);
        }

        return new SellerIdentity(parseSellerId(payload.sellerNo()));
    }

    private UUID parseSellerId(String sellerNo) {
        try {
            return UUID.fromString(sellerNo);
        } catch (IllegalArgumentException ex) {
            throw new IllegalStateException("Invalid seller id from external system. sellerNo=" + sellerNo,
                    ex);
        }
    }
}
