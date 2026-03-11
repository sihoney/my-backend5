package com.example.demo.member.presentation.controller;

import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.domain.model.Member;
import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.request.Login;
import com.example.demo.member.presentation.dto.response.MemberAdminResponse;
import com.example.demo.member.presentation.dto.response.MemberResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * 서비스가 아닌 유스케이스 호출
 */

@RestController
@RequestMapping("/api/member")
@Tag(name = "Member", description = "사용자 CRUD API")
@RequiredArgsConstructor
public class MemberController {

    public final MemberUseCase memberUseCase;

    @GetMapping("findAll")
    public ResponseEntity<List<MemberResponse>> getAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberUseCase.findAll());
    }

    @GetMapping("findAdminAll")
    public ResponseEntity<List<MemberAdminResponse>> getAdminAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberUseCase.findAdmAll());
    }

    @PostMapping("join")
    public ResponseEntity<MemberResponse> join(
            @RequestBody CreateMemberRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(memberUseCase.create(request));
    }

    @PostMapping("login")
    public ResponseEntity<Boolean> login(
            @RequestBody Login login
    ) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(memberUseCase.login(login));
    }
}
