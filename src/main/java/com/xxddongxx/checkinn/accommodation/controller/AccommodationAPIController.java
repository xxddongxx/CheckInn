package com.xxddongxx.checkinn.accommodation.controller;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.service.AccommodationService;
import com.xxddongxx.checkinn.util.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/accommodations")
@Tag(name = "02. Accommodation", description = "Accommodation API 입니다.")
public class AccommodationAPIController {
    private Logger logger = LoggerFactory.getLogger(AccommodationAPIController.class);
    private AccommodationService accommodationService;

    @Autowired
    public AccommodationAPIController(AccommodationService accommodationService){
        this.accommodationService = accommodationService;
    }

    /**
     * 숙박업소 생성 api
     * @param accommodationDto
     * */
    @PostMapping
    @Operation(summary = "Accommodation 생성", description = "숙박업소 등록")
    @ApiResponse(responseCode = "201", description = "숙박업소가 정상적으로 등록되었습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> insertAccommodation(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AccommodationDto accommodationDto){
        logger.info("Insert Accommodation");

        int result = accommodationService.insertAccommodation(accommodationDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(result));
    }


    /**
     * 숙박업소 조회 api
     * @param idx
     * */
    @GetMapping("/{idx}")
    @Operation(summary = "숙박업소 조회", description = "특정 숙박업소 조회")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "해당 수박업소를 찾을 수 없습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<AccommodationDto>> selectByAccommodation(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("idx") long idx) {
        logger.info("select accommodation");

        AccommodationDto accommodation = accommodationService.selectByAccommodation(idx);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(accommodation));

    }

    /**
     * 숙박업소 수정 api
     * @param idx
     * */
    @PutMapping("/{idx}")
    @Operation(summary = "숙박업소 수정", description = "특정 숙박업소 수정")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "해당 수박업소를 찾을 수 없습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<AccommodationDto>> updateAccommodation(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("idx") long idx,
            @RequestBody AccommodationDto accommodationDto) {
        logger.info("update accommodation");

        AccommodationDto accommodation = accommodationService.updateAccommodation(idx, accommodationDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(accommodation));

    }

    @GetMapping
    @Operation(summary = "숙박업소 전체 조회", description = "숙박업소 전체 조회")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "숙박업소를 찾지 못했습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<List<AccommodationDto>>> selectByAllAccommodation(
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("select All Accommodation");

        List<AccommodationDto> accommodationDtoList = accommodationService.selectByAllAccommodation();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(accommodationDtoList));
    }
}
