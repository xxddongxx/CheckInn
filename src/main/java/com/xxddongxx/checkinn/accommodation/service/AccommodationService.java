package com.xxddongxx.checkinn.accommodation.service;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.mapper.AccommodationMapper;
import com.xxddongxx.checkinn.accommodation.model.Accommodation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {
    private Logger logger = LoggerFactory.getLogger(AccommodationService.class);
    private AccommodationMapper accommodationMapper;

    public AccommodationService(AccommodationMapper accommodationMapper) {
        this.accommodationMapper = accommodationMapper;
    }

    public void insertAccommodation(AccommodationDto accommodationDto) {
        accommodationMapper.insertAccommodation(new Accommodation().toEntity(accommodationDto));
    }
}
