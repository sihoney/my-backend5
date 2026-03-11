package com.example.demo.product.application.acl;

import java.util.UUID;

/**
 * ACL 포트(내부 인터페이스)
 * - Application(서비스)은 'SellerAcl' 인터페이스만 의존
 */

public interface SellerAcl {

    SellerIdentity loadActiveSeller(UUID sellerId);
}
