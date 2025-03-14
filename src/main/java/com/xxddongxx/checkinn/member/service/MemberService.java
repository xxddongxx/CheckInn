package com.xxddongxx.checkinn.member.service;

import com.xxddongxx.checkinn.config.exception.CustomException;
import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.mapper.MemberMapper;
import com.xxddongxx.checkinn.member.model.Member;
import com.xxddongxx.checkinn.member.model.MemberDetails;
import com.xxddongxx.checkinn.member.model.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private MemberMapper memberMapper;
    private PasswordEncoder passwordEncoder;

    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<Member> member = Optional.ofNullable(memberMapper.selectByMember(userId));

        if (!member.isPresent()){
            throw new IllegalArgumentException("User not authorized.");
        }
        return new MemberDetails(member.get());
    }

    public int insertMember(MemberDto memberDto) {

        if(isDuplicate(memberDto.getUserId())){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        String password = memberDto.getPassword();
        memberDto.setPassword(passwordEncoder.encode(password));
        if(memberDto.getRole() == null){
            memberDto.setRole(Role.STAFF);
        }


        return memberMapper.insertMember(new Member().toEntity(memberDto));
    }

    public boolean isDuplicate(String userId) {
        return memberMapper.isDuplicate(userId);
    }

    public boolean isOwner(String ownerId) {
        return memberMapper.isOwner(ownerId);
    }

}
