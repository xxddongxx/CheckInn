package com.xxddongxx.checkinn.member.controller;

import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.service.MemberService;
import com.xxddongxx.checkinn.util.Message;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "01. Member", description = "Member API 입니다.")
public class MemberAPIController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAPIController.class);

    private MemberService memberService;

    public MemberAPIController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    @Operation(summary = "Member 생성", description = "Member 정보를 생성합니다.")
    @ApiResponse(responseCode = "201", description = "멤버가 성공적으로 등록되었습니다.", content = @Content(schema = @Schema(implementation = Message.class)))
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> insertMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody MemberDto memberDto){

        int result = memberService.insertMember(memberDto);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Message.success("멤버가 성공적으로 등록되었습니다.", result));
    }
}
