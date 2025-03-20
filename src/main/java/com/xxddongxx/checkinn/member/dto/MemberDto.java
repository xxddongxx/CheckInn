package com.xxddongxx.checkinn.member.dto;

import com.xxddongxx.checkinn.member.model.Member;
import com.xxddongxx.checkinn.member.model.Role;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
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

    public MemberDto toDto(Member member) {
        return MemberDto.builder()
                .idx(member.getIdx())
                .userId(member.getUserId())
                .nm(member.getNm())
                .role(member.getRole())
                .email(member.getEmail())
                .phone(member.getPhone())
                .deleteAt(member.getDeleteAt())
                .createdAt(member.getCreatedAt())
                .updatedAt(member.getUpdatedAt())
                .build();
    }
}
