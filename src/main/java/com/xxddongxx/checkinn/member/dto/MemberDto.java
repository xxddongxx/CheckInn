package com.xxddongxx.checkinn.member.dto;

import com.xxddongxx.checkinn.member.model.Member;
import com.xxddongxx.checkinn.member.model.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
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
}
