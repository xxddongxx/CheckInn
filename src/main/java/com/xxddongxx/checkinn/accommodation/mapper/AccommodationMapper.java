package com.xxddongxx.checkinn.accommodation.mapper;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.model.Accommodation;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccommodationMapper {
    int insertAccommodation(Accommodation accommodation);
    Accommodation selectByAccommodation(long idx);
}
