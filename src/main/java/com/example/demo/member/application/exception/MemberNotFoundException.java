package com.example.demo.member.application.exception;

import java.util.UUID;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(UUID memberId) {
        super("Member not found. memberId=" + memberId);
    }
}
