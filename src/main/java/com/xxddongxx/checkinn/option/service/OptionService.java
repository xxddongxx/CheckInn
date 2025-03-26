package com.xxddongxx.checkinn.option.service;

import com.xxddongxx.checkinn.config.exception.CustomException;
import com.xxddongxx.checkinn.option.dto.OptionDto;
import com.xxddongxx.checkinn.option.mapper.OptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OptionService {
    private static final Logger logger = LoggerFactory.getLogger(OptionService.class);

    private OptionMapper optionMapper;

    public OptionService(OptionMapper optionMapper) {
        this.optionMapper = optionMapper;
    }

    @Transactional(readOnly = true)
    public OptionDto selectByOption(long idx) {
        OptionDto selectOptionDto = optionMapper.selectByOption(idx);

        if(selectOptionDto == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "옵션을 찾을 수 없습니다.");
        }
        return selectOptionDto;
    }

    @Transactional(readOnly = true)
    public List<OptionDto> selectByAllOption() {
        return optionMapper.selectByAllOption();
    }

    @Transactional
    public int insertOption(OptionDto optionDto) {
        return optionMapper.insertOption(optionDto);
    }

    @Transactional
    public OptionDto updateOption(long idx, OptionDto optionDto) {
        OptionDto selectOption = optionMapper.selectByOption(idx);

        if(selectOption == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, "옵션을 찾을 수 없습니다.");
        }

        optionMapper.updateOption(optionDto);
        return optionMapper.selectByOption(idx);
    }
}
