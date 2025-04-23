package com.xxddongxx.checkinn.accommodation.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccommodationOptionMapper {
    int insertAccOption(List<Long> optionIdxList);
}
