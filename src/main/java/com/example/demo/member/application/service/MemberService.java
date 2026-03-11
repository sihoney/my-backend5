package com.example.demo.member.application.service;

import com.example.demo.common.ResponseEntity;
import com.example.demo.member.application.usecase.MemberUseCase;
import com.example.demo.member.domain.model.Member;
import com.example.demo.member.domain.repository.MemberRepository;
import com.example.demo.member.presentation.dto.request.CreateMemberRequest;
import com.example.demo.member.presentation.dto.request.LoginRequest;
import com.example.demo.member.presentation.dto.response.MemberAdminResponse;
import com.example.demo.member.presentation.dto.response.MemberResponse;
import com.example.demo.member.util.JwtProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * useCase를 구현
 */

@Service
@Transactional(readOnly = true)
//@RequiredArgsConstructor
@Slf4j
public class MemberService {

    @Autowired
    public MemberRepository memberRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtProvider jwtProvider;

//    @Override
    public List<MemberResponse> findAll() {
        return memberRepository.findAll().stream()
                .map(this::changeMemberResType)
                .toList();
    }

//    @Override
    public List<MemberAdminResponse> findAdmAll() {
        return memberRepository.findAll().stream()
                .map(this::changeMemberAdmResType)
                .toList();
    }

    @Transactional
//    @Override
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
                    passwordEncoder.encode(request.password() + member.getSaltKey())
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

//    @Override
    public ResponseEntity<HashMap<String, Object>> login(
            LoginRequest loginRequest
    ) {
        Optional<Member> memberOptional = memberRepository.findByEmail(loginRequest.email());

        HashMap<String, Object> res = new HashMap<>();
        if(memberOptional.isPresent()){
            Member memeber = memberOptional.get();

            if(passwordEncoder.matches(loginRequest.password(), memeber.getPassword())){
                Authentication authentication = new UsernamePasswordAuthenticationToken(memeber.getId().toString(), null);
                res.put("access-token", jwtProvider.generateToken(authentication));
                res.put("refresh-token", jwtProvider.generateRefreshToken(authentication));
                return new ResponseEntity<>(HttpStatus.OK.value(), res, 1);
            }else{
                throw new IllegalArgumentException("password is not correct");
            }
        }
        return null;
    }

    // TODO: saltkey가 뭐지?
    // TODO: 왜 패스워드와 saltkey를 더해서 사용하지?
//    public Boolean login(LoginRequest login) {
//        Member member = memberRepository.findByEmail(login.email());
////        encoder.encode(login.password());
//
//        if(encoder.matches(
//                login.password() + member.getSaltKey(),
//                member.getPassword()
//        )) {
//            // TODO: jwt 생성
//            return true;
//        }
//
//        return false;
//    }

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

