package com.xxddongxx.checkinn.accommodation.controller;

import com.xxddongxx.checkinn.accommodation.dto.AccommodationDto;
import com.xxddongxx.checkinn.accommodation.model.Accommodation;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping
    @Operation(summary = "Accommodation 생성", description = "숙박업소 등록")
    @ApiResponse(responseCode = "201", description = "숙박업소가 정상적으로 등록되었습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> insertAccommodation(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody AccommodationDto accommodationDto){
        logger.info("Insert Accommodation");

        accommodationService.insertAccommodation(accommodationDto);


        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(null));
    }


}
