package com.xxddongxx.checkinn.member.model;

import com.xxddongxx.checkinn.member.dto.MemberDto;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private long idx;
    private String userId;
    private String password;
    private String nm;
    private Role role;
    private String email;
    private String phone;
    private char deleteAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Member toEntity(MemberDto memberDto) {
        return Member.builder()
                .idx(memberDto.getIdx())
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .nm(memberDto.getNm())
                .role(memberDto.getRole())
                .email(memberDto.getEmail())
                .phone(memberDto.getPhone())
                .deleteAt(memberDto.getDeleteAt())
                .createdAt(memberDto.getCreatedAt())
                .updatedAt(memberDto.getUpdatedAt())
                .build();
    }
}
