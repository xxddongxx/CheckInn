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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "01. Member", description = "Member API 입니다.")
public class MemberAPIController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAPIController.class);

    private MemberService memberService;

    @Autowired
    public MemberAPIController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 맴버 생성 api
     * @param memberDto
     * */
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

    /**
     * 아이디 중복 확인 api
     * @param userId
     * */
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

    /**
     * 숙박업소 사장 권한 확인 api
     * @param ownerId
     * */
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

    /**
     * 멤버 조회 api
     * @param idx
     * */
    @GetMapping("/{idx}")
    @Operation(summary = "멤버 조회", description = "특정 멤버 조회")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "해당 유적를 찾을 수 없습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<MemberDto>> selectByMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("idx") long idx) {
        logger.info("select Member");

        MemberDto resultMemberDto = memberService.selectByMember(idx);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(resultMemberDto));
    }

    /**
     * 멤버 정보 수정 api
     * @param idx
     * @param memberDto
     * */
    @PutMapping("/{idx}")
    @Operation(summary = "유저 정보 수정", description = "특정 유저 정보 수정")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "해당 유저를 찾을 수 없습니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<MemberDto>> updateByMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("idx") long idx,
            @RequestBody MemberDto memberDto) {
        logger.info("update member");

        MemberDto resultMember = memberService.updateMember(idx, memberDto);

        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(resultMember));
    }

    /**
     * 멤버 삭제 처리 api
     * @param idx
     * */
    @PatchMapping("/{idx}")
    @Operation(summary = "유저 삭제", description = "특정 유저 삭제 처리")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다ㅏ.")
    @ApiResponse(responseCode = "404", description = "해당 유저를 찾을 수 없습니다.")
    @ApiResponse(responseCode = "500", description = " 서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> deleteMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @PathVariable("idx") long idx) {
        logger.info("delete Member");
        int result = memberService.deleteMember(idx);
        
        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(result));
    }

    /**
     * 멤버 전체 조회 api
     * */
    @GetMapping
    @Operation(summary = "멤버 전체 조회", description = "멤버 전체 조회")
    @ApiResponse(responseCode = "200", description = "요청이 성공적으로 처리되었습니다.")
    @ApiResponse(responseCode = "404", description = "잘못된 요청입니다.")
    @ApiResponse(responseCode = "500", description = "서버 오류가 발생했습니다.")
    public ResponseEntity<Message<?>> selectByAllMember(
            HttpServletRequest request,
            HttpServletResponse response) {
        logger.info("get all member");

        List<MemberDto> memberDtoList = memberService.selectByAllMember();

        return ResponseEntity.status(HttpStatus.OK)
                .body(Message.success(memberDtoList));
    }
}
