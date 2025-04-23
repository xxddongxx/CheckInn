package com.xxddongxx.checkinn.option.mapper;

import com.xxddongxx.checkinn.option.dto.OptionDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OptionMapper {
    int insertOption(OptionDto optionDto);
    int updateOption(OptionDto optionDto);
    OptionDto selectByOption(Long idx);
    List<OptionDto> selectByAllOption();
}
