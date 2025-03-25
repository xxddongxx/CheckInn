package com.xxddongxx.checkinn.accommodation.mapper;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccommodationMapper {
    int insertAccommodation(AccommodationDto accommodation);
    AccommodationDto selectByAccommodation(long idx);
    int updateAccommodation(AccommodationDto accommodationDto);
    List<AccommodationDto> selectByAllAccommodation();
}
