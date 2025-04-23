package com.xxddongxx.checkinn.accommodation.service;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.mapper.AccommodationMapper;
import com.xxddongxx.checkinn.accommodation.mapper.AccommodationOptionMapper;
import com.xxddongxx.checkinn.config.exception.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AccommodationService {
    private Logger logger = LoggerFactory.getLogger(AccommodationService.class);
    private AccommodationMapper accommodationMapper;
    private AccommodationOptionMapper accOptionMapper;

    @Autowired
    public AccommodationService(AccommodationMapper accommodationMapper, AccommodationOptionMapper accOptionMapper) {
        this.accommodationMapper = accommodationMapper;
        this.accOptionMapper = accOptionMapper;
    }

    @Transactional
    public int insertAccommodation(AccommodationDto accommodationDto) {
        int result = 0;
        try {
            result += accommodationMapper.insertAccommodation(accommodationDto);
            result += accOptionMapper.insertAccOption(accommodationDto.getOptionIdxList());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

        return result;
    }

    @Transactional(readOnly = true)
    public AccommodationDto selectByAccommodation(Long idx){
        AccommodationDto accommodation = accommodationMapper.selectByAccommodation(idx);

        if(accommodation == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 수박업소를 찾을 수 없습니다.");
        }

        return accommodation;
    }

    @Transactional
    public AccommodationDto updateAccommodation(Long idx, AccommodationDto accommodationDto) {
        AccommodationDto selectAccommodation = accommodationMapper.selectByAccommodation(idx);

        if(selectAccommodation == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 수박업소를 찾을 수 없습니다.");
        }

        accommodationMapper.updateAccommodation(accommodationDto);

        return accommodationMapper.selectByAccommodation(idx);
    }

    @Transactional(readOnly = true)
    public List<AccommodationDto> selectByAllAccommodation(){
        return accommodationMapper.selectByAllAccommodation();
    }

    @Transactional
    public int deleteAccommodation(Long idx) {
        AccommodationDto selectAccommodation = accommodationMapper.selectByAccommodation(idx);

        if(selectAccommodation == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 수박업소를 찾을 수 없습니다.");
        }
        return accommodationMapper.deleteAccommodation(idx);
    }
}
