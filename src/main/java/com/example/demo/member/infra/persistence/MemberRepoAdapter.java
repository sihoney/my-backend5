package com.example.demo.member.infra.persistence;

import com.example.demo.member.domain.model.Member;
import com.example.demo.member.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class MemberRepoAdapter implements MemberRepository {

    public final MemberJpaRepository memberJpaRepository;

    @Override
    public List<Member> findAll() {
        return memberJpaRepository.findAll();
    }

    @Override
    public Member save(Member member) {
        return memberJpaRepository.save(member);
    }

    @Override
    public Optional<Member> findById(UUID memberId) {
        return memberJpaRepository.findById(memberId);
    }

    @Override
    public boolean findByPhone(String phone) {
        return memberJpaRepository.findByPhone(phone).isPresent();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberJpaRepository.findByEmail(email);
    }
}