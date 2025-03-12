package com.xxddongxx.checkinn.member.controller;

import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.service.MemberService;
import com.xxddongxx.checkinn.util.Message;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberAPIController {
    private static final Logger logger = LoggerFactory.getLogger(MemberAPIController.class);

    private MemberService memberService;

    public MemberAPIController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/")
    public ResponseEntity<Message<?>> insertMember(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody MemberDto memberDto){

        int result = memberService.insertMember(memberDto);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Message.success("멤버가 성공적으로 등록되었습니다.", result));
    }
}
