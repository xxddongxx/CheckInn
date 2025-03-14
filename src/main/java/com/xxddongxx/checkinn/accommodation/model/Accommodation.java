package com.xxddongxx.checkinn.accommodation.model;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.member.model.Member;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Accommodation {
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

    public Accommodation toEntity(AccommodationDto accommodationDto) {
        String fullAddress = accommodationDto.getAccAddress() + ' ' + accommodationDto.getAccDetailAddress() +  ' ' + accommodationDto.getAccExtraAddress();
        return Accommodation.builder()
                .idx(accommodationDto.getIdx())
                .accNm(accommodationDto.getAccNm())
                .accPostCode(accommodationDto.getAccPostCode())
                .accAddress(accommodationDto.getAccAddress())
                .accDetailAddress(accommodationDto.getAccDetailAddress())
                .accExtraAddress(accommodationDto.getAccExtraAddress())
                .accFullAddress(fullAddress)
                .accPhone(accommodationDto.getAccPhone())
                .accType(accommodationDto.getAccType())
                .chiAvailableTile1(accommodationDto.getChiAvailableTile1())
                .chiAvailableTime2(accommodationDto.getChiAvailableTime2())
                .choAvailableTime(accommodationDto.getChoAvailableTime())
                .ownerNm(accommodationDto.getOwnerNm())
                .ownerPhone(accommodationDto.getOwnerPhone())
                .accAgreement1(accommodationDto.getAccAgreement1())
                .accAgreement2(accommodationDto.getAccAgreement2())
                .accAgreement3(accommodationDto.getAccAgreement3())
                .manager(accommodationDto.getManager())
                .isActive(accommodationDto.isActive())
                .createdAt(accommodationDto.getCreatedAt())
                .updatedAt(accommodationDto.getUpdatedAt())
                .build();
    }
}
