package com.example.demo.member.application.service;

import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.domain.model.Member;
import com.example.demo.member.domain.repository.MemberRepository;
import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.request.Login;
import com.example.demo.member.presentation.dto.response.MemberAdminResponse;
import com.example.demo.member.presentation.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

/**
 * useCase를 구현
 */

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService implements MemberUseCase {

    public final MemberRepository memberRepository;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(this::changeMemberResType)
                .toList();
    }

    @Override
    public List<MemberAdminResponse> findAdmAll() {
        return memberRepository.findAll().stream()
                .map(this::changeMemberAdmResType)
                .toList();
    }

    @Transactional
    @Override
    public MemberResponse create(CreateMemberRequest request) {

        if(!memberRepository.findByPhone(request.phone())) {
            SecureRandom random = new SecureRandom();
            byte[] saltkey = random.generateSeed(8);

            Member member = Member.create(
                    request.id(),
                    request.email(),
                    request.name(),
                    request.password(),
                    request.phone(),
                    request.address()
            );
            // saltkey 생성, member 객체에 저장
            member.setSaltKey(
                    Base64.getEncoder().encodeToString(saltkey)
            );
            member.setPassword(
                    encoder.encode(request.password() + member.getSaltKey())
            );

            memberRepository.save(member);
            return new MemberResponse(
                    member.getId(),
                    member.getName(),
                    member.getAddress()
            );
        } else {
            // TODO: throw (이미 사용 중인 핸드폰 번호)
        }

        return null;
    }

    // TODO: saltkey가 뭐지?
    // TODO: 왜 패스워드와 saltkey를 더해서 사용하지?
    public Boolean login(Login login) {
        Member member = memberRepository.findByEmail(login.email());
//        encoder.encode(login.password());

        if(encoder.matches(
                login.password() + member.getSaltKey(),
                member.getPassword()
        )) {
            // TODO: jwt 생성
            return true;
        }

        return false;
    }

    private MemberResponse changeMemberResType(Member member) {
        return new MemberResponse(
                member.getId(),
                member.getName(),
                member.getAddress()
        );
    }

    private MemberAdminResponse changeMemberAdmResType(Member member) {
        return new MemberAdminResponse(
                member.getId(),
                member.getName(),
                member.getAddress(),
                member.getEmail(),
                member.getPhone(),
                member.getStatus(),
                member.getRegId(),
                member.getRegDt(),
                member.getModifyId(),
                member.getModifyDt(),
                member.getFlag()
        );
    }

//    private Member findByIdOrThrow(UUID memberId) {
//        return memberRepository.findById(memberId)
//                .orElseThrow(() -> new MemberNotFoundException(memberId));
//    }
}

