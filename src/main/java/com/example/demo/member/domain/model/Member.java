package com.example.demo.member.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "\"member\"", schema = "public")
public class Member {

    @Schema(description = "유저의 UUID")
    @Id
    private UUID id;

    @Schema(description = "유저의 email")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Schema(description = "유저명")
    @Column(name = "\"name\"", length = 20)
    private String name;

    @Schema(description = "비밀번호")
    @Column(name = "\"password\"", nullable = false, length = 100)
    private String password;

    @Schema(description = "핸드폰번호")
    @Column(nullable = false, length = 20, unique = true)
    private String phone;

    @Schema(description = "주소")
    @Column(nullable = false, length = 100)
    private String address;

    @Schema(description = "유저상태")
    @Column(name = "\"status\"", length = 5)
    private String status;

    @Column(name = "reg_id", nullable = false)
    private UUID regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Column(name = "modify_id", nullable = false)
    private UUID modifyId;

    @Column(name = "modify_dt", nullable = false)
    private LocalDateTime modifyDt;

    @Column(name = "saltkey", nullable = false, length = 14)
    private String saltKey;

    @Column(name = "flag", length = 5)
    private String flag;

    protected Member() {
    }

    private Member(
            UUID id,
            String email,
            String name,
            String password,
            String phone,
            String address
    ) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.address = address;
    }

    public static Member create(
            UUID id,
            String email,
            String name,
            String password,
            String phone,
            String address
    ) {
        Member member = new Member(
                UUID.randomUUID(),
                email,
                name,
                password,
                phone,
                address
        );

        return member;
    }

    @PrePersist
    public void onCreate() {
        if(id == null) {
            id = UUID.randomUUID();
        }

//        if(status == null) {
//            status = "GOOD";
//        }

        if(regId == null) {
            regId = id;
        }

        if(regDt == null) {
            regDt = LocalDateTime.now();
        }

        if(modifyId == null) {
            modifyId = regId;
        }

        if(modifyDt == null) {
            modifyDt = regDt;
        }
    }
}
