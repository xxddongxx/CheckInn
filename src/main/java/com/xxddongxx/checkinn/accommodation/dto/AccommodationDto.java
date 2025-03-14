package com.xxddongxx.checkinn.accommodation.dto;

import com.xxddongxx.checkinn.member.model.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Getter
@Setter
public class AccommodationDto {
    private long idx;
    private String accNm;
    private String accPostCode;
    private String accAddress;
    private String accDetailAddress;
    private String accExtraAddress;
    private String accFullAddress;
    private String accPhone;
    private String accType;
    private LocalTime chiAvailableTile1;
    private LocalTime chiAvailableTime2;
    private LocalTime choAvailableTime;
    private String ownerNm;
    private String ownerPhone;
    private String accAgreement1;
    private String accAgreement2;
    private String accAgreement3;
    private Member manager;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
