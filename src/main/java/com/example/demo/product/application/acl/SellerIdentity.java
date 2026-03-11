package com.example.demo.product.application.acl;

import java.util.UUID;

/**
 * - 내부 모델
 * - Infrastructure의 'SellerAclAdapter'가 외부 응답을 번역
 * - 번연된 내부 모델('SellerIdentity')만 서비스에서 사용
 */

public record SellerIdentity(UUID id) {

}
