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
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "01. Member", description = "Member API 입니다.")
public class MemberAPIController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAPIController.class);

    private MemberService memberService;

    public MemberAPIController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/sign_up")
    @Operation(summary = "Member 생성", description = "Member 정보를 생성합니다.")
    @ApiResponse(responseCode = "201", description = "멤버가 성공적으로 등록되었습니다.", content = @Content(schema = @Schema(implementation = Message.class)))
    @ApiResponse(responseCode = "400", description = "이미 존재하는 사용자입니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> insertMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody MemberDto memberDto){
        logger.info("Insert Member");
        int result = memberService.insertMember(memberDto);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Message.success("멤버가 성공적으로 등록되었습니다.", result));
    }

    @GetMapping("/checkId")
    @Operation(summary = "아이디 중복 확인", description = "Member 아이디 중복 확인")
    @ApiResponse(responseCode = "200", description = "사용 가능한 아이디입니다.")
    @ApiResponse(responseCode = "400", description = "중복된 아이디가 존재합니다.")
    public ResponseEntity<Message<?>> duplicateCheckUserId(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam String userId) {
        logger.info("check duplicate userId");
        boolean result = memberService.isDuplicate(userId);

        if(!result){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Message.success("사용 가능한 아이디입니다.", null));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Message.error(HttpStatus.BAD_REQUEST, "중복된 아이디가 존재합니다."));
    }

    @GetMapping("/searchOwner")
    @Operation(summary = "대표권한 확인", description = "Member Owner 권한 확인")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "400", description = "권한이 없는 아이디입니다.")
    public ResponseEntity<Message<?>> searchOwner(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam String ownerId) {
        logger.info("check ownerId");
        boolean result = memberService.isOwner(ownerId);

        if(result){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(Message.success(null));
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Message.error(HttpStatus.BAD_REQUEST, "권한이 없는 아이디입니다."));
    }
}
