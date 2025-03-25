package com.xxddongxx.checkinn.accommodation.mapper;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccommodationMapper {
    int insertAccommodation(AccommodationDto accommodation);
    AccommodationDto selectByAccommodation(long idx);
}
