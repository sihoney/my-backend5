package com.example.demo.member.application.usecase;

import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.request.LoginRequest;
import com.example.demo.member.presentation.dto.response.MemberAdminResponse;
import com.example.demo.member.presentation.dto.response.MemberResponse;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

// Member 반환 X (개인정보 보호)

public interface MemberUseCase {
    List<MemberResponse> findAll();
    List<MemberAdminResponse> findAdmAll();

    MemberResponse create(CreateMemberRequest request);

    ResponseEntity<HashMap<String, Object>> login(LoginRequest loginRequest);
//    Boolean login(LoginRequest login);
}
