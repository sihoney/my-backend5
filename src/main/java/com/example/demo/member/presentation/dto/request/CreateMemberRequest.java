package com.example.demo.member.presentation.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.util.UUID;

@Schema(description = "회원 생성 요청")
public record CreateMemberRequest (
        
        @Schema(description = "유저의 UUID")
        UUID id,

        @Schema(description = "유저의 email")
        String email,
        
        @Schema(description = "유저명")
        String name,
        
        @Schema(description = "비밀번호")
        String password,
        
        @Schema(description = "핸드폰번호")
        String phone,
        
        @Schema(description = "주소")
        String address
        
//        @Schema(description = "유저상태")
//        @Column(name = "\"status\"", length = 5)
//        String status,
//
//        @Column(name = "reg_id", nullable = false)
//        UUID regId,
//
//        @Column(name = "reg_dt", nullable = false)
//        LocalDateTime regDt,
//
//        @Column(name = "modify_id", nullable = false)
//        UUID modifyId,
//
//        @Column(name = "modify_dt", nullable = false)
//        LocalDateTime modifyDt,
//
//        @Column(name = "saltkey", nullable = false, length = 14)
//        String saltKey,
//
//        @Column(name = "flag", length = 5)
//        String flag
){
}
