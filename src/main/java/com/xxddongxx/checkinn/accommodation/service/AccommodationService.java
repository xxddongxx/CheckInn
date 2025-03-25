package com.xxddongxx.checkinn.accommodation.service;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.mapper.AccommodationMapper;
import com.xxddongxx.checkinn.accommodation.model.Accommodation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationService {
    private Logger logger = LoggerFactory.getLogger(AccommodationService.class);
    private AccommodationMapper accommodationMapper;

    @Autowired
    public AccommodationService(AccommodationMapper accommodationMapper) {
        this.accommodationMapper = accommodationMapper;
    }

    @Transactional
    public int insertAccommodation(AccommodationDto accommodationDto) {
        int result = accommodationMapper.insertAccommodation(new Accommodation().toEntity(accommodationDto));
        return result;
    }

    @Transactional(readOnly = true)
    public AccommodationDto selectByAccommodation(long idx){
        Accommodation accommodation = accommodationMapper.selectByAccommodation(idx);
        return new AccommodationDto().toDto(accommodation);
    }
}
