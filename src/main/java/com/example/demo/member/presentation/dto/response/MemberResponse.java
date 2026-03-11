package com.example.demo.member.presentation.dto.response;

import com.example.demo.member.domain.model.Member;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "회원 응답")
public record MemberResponse(
        UUID id,
        String name,
        String address
) {

    public static MemberResponse from(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAddress()
        );
    }
}
