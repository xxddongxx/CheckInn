package com.xxddongxx.checkinn.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.dto.Role;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationDto {
    private long idx;
    private String accNm;
    private String accPostCode;
    private String accFullAddress;
    private String accPhone;
    private String accType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime chiAvailableTime1;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime chiAvailableTime2;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime choAvailableTime;
    private String ownerNm;
    private String ownerPhone;
    private String accAgreement1;
    private String accAgreement2;
    private String accAgreement3;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Manager
    private String managerId;
    private String managerNm;
    private Role managerRole;
    private String managerEmail;
    private String managerPhone;
}
