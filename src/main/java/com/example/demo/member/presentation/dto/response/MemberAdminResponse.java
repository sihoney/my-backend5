package com.example.demo.member.presentation.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

// 관리자용

public record MemberAdminResponse(
        UUID id,
        String name,
        String address,
        String email,
        String phone,
        String status,
        UUID regId,
        LocalDateTime regDt,
        UUID modifyId,
        LocalDateTime modifyDt,
        String flag
) {
}
