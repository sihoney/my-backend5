package com.example.demo.member.domain.repository;

import com.example.demo.member.domain.model.Member;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberRepository {
    List<Member> findAll();

    Member save(Member member);

    Optional<Member> findById(UUID memberId);

    boolean findByPhone(String phone);

    Optional<Member> findByEmail(String email);
}
