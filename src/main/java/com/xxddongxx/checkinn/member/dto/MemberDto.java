package com.xxddongxx.checkinn.member.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long idx;
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
