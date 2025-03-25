package com.xxddongxx.checkinn.accommodation.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxddongxx.checkinn.accommodation.model.Accommodation;
import com.xxddongxx.checkinn.member.model.Member;
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
    private String accAddress;
    private String accDetailAddress;
    private String accExtraAddress;
    private String accFullAddress;
    private String accPhone;
    private String accType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime chiAvailableTile1;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime chiAvailableTime2;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
    private LocalTime choAvailableTime;
    private String ownerNm;
    private String ownerPhone;
    private String accAgreement1;
    private String accAgreement2;
    private String accAgreement3;
    private long managerIdx;
    private Member manager;
    private boolean isActive;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public AccommodationDto toDto(Accommodation accommodation) {
        return AccommodationDto.builder()
                .idx(accommodation.getIdx())
                .accNm(accommodation.getAccNm())
                .accPostCode(accommodation.getAccPostCode())
                .accAddress(accommodation.getAccAddress())
                .accDetailAddress(accommodation.getAccDetailAddress())
                .accExtraAddress(accommodation.getAccExtraAddress())
                .accFullAddress(accommodation.getAccFullAddress())
                .accPhone(accommodation.getAccPhone())
                .accType(accommodation.getAccType())
                .chiAvailableTile1(accommodation.getChiAvailableTile1())
                .chiAvailableTime2(accommodation.getChiAvailableTime2())
                .choAvailableTime(accommodation.getChoAvailableTime())
                .ownerNm(accommodation.getOwnerNm())
                .ownerPhone(accommodation.getOwnerPhone())
                .accAgreement1(accommodation.getAccAgreement1())
                .accAgreement2(accommodation.getAccAgreement2())
                .accAgreement3(accommodation.getAccAgreement3())
                .managerIdx(accommodation.getManagerIdx())
                .manager(accommodation.getManager())
                .isActive(accommodation.isActive())
                .createdAt(accommodation.getCreatedAt())
                .updatedAt(accommodation.getUpdatedAt())
                .build();
    }
}
