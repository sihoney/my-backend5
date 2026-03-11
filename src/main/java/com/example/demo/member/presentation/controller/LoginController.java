package com.example.demo.member.presentation.controller;

import com.example.demo.common.ResponseEntity;
import com.example.demo.member.application.service.MemberService;
import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.presentation.dto.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
public class LoginController {

//    private final MemberService memberService;
//    private final MemberUseCase memberUseCase;
    private final MemberService memberService;

    @PostMapping("${api.v1}/login")
    public ResponseEntity<HashMap<String, Object>> login(
            @RequestBody LoginRequest loginRequest
    ){
        return memberService.login(loginRequest);
//        return memberService.login(loginRequest);
    }

//    @GetMapping("${api.v1}/authorizations/check")
//    public Boolean check(
//            @RequestParam("httpMethod") String httpMethod,
//            @RequestParam("requestPath") String requestPath
//    ){
//        return memberService.check(httpMethod, requestPath);
////        return memberService.check(httpMethod, requestPath);
//    }

//    @GetMapping("${api.v1}/refresh/token")
//    public ResponseEntity<HashMap<String, Object>> refreshToken(
//            @RequestHeader("refresh-token") String token
//    ){
//        return memberUseCase.refreshToken(token);
////        return memberService.refreshToken(token);
//    }
}
