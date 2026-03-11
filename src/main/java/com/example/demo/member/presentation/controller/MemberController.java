package com.example.demo.member.presentation.controller;

import com.example.demo.member.application.service.MemberService;
import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.response.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 서비스가 아닌 유스케이스 호출
 */

@RestController
@RequestMapping("${api.v1}/members")
//@RequestMapping("${api.v1}/members")
@Tag(name = "Member", description = "사용자 CRUD API")
@RequiredArgsConstructor
public class MemberController {

    public final MemberService memberUseCase;
//    public final MemberUseCase memberUseCase;

    @Operation(
            summary = "회원 목록 조회",
            description = "public.member 테이블에 저장된 모든 회원을 조회한다."
    )
    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberUseCase.findAll());
    }

//    @GetMapping("findAdminAll")
//    public ResponseEntity<List<MemberAdminResponse>> getAdminAll() {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(memberUseCase.findAdmAll());
//    }

    @Operation(
            summary = "회원 등록",
            description = "요청으로 받은 회원 정보를 public.member 테이블에 저장한다."
    )
    @PostMapping
    public ResponseEntity<MemberResponse> join(
            @RequestBody CreateMemberRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberUseCase.create(request));
    }

//    @PostMapping("login")
//    public ResponseEntity<Boolean> login(
//            @RequestBody LoginRequest login
//    ) {
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(memberUseCase.login(login));
//    }
}
