package com.example.demo.member.application.usecase;

import com.example.demo.member.domain.model.Member;
import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.request.Login;
import com.example.demo.member.presentation.dto.response.MemberAdminResponse;
import com.example.demo.member.presentation.dto.response.MemberResponse;

import java.util.List;
import java.util.UUID;

// Member 반환 X (개인정보 보호)

public interface MemberUseCase {
    List<MemberResponse> findAll();
    List<MemberAdminResponse> findAdmAll();

    MemberResponse create(CreateMemberRequest request);

    Boolean login(Login login);
}
