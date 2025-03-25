package com.xxddongxx.checkinn.member.service;

import com.xxddongxx.checkinn.config.exception.CustomException;
import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.mapper.MemberMapper;
import com.xxddongxx.checkinn.member.dto.MemberDetails;
import com.xxddongxx.checkinn.member.dto.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(MemberService.class);

    private MemberMapper memberMapper;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public MemberService(MemberMapper memberMapper, PasswordEncoder passwordEncoder) {
        this.memberMapper = memberMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        MemberDto member = memberMapper.loadUsrByUsername(userId);

        if (member == null){
            throw new IllegalArgumentException("User not authorized.");
        }
        return new MemberDetails(member);
    }

    @Transactional
    public int insertMember(MemberDto memberDto) {

        if(isDuplicate(memberDto.getUserId())){
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }

        String password = memberDto.getPassword();
        memberDto.setPassword(passwordEncoder.encode(password));
        if(memberDto.getRole() == null){
            memberDto.setRole(Role.STAFF);
        }

        return memberMapper.insertMember(memberDto);
    }

    @Transactional(readOnly = true)
    public boolean isDuplicate(String userId) {
        return memberMapper.isDuplicate(userId);
    }

    @Transactional(readOnly = true)
    public boolean isOwner(String ownerId) {
        return memberMapper.isOwner(ownerId);
    }

    @Transactional(readOnly = true)
    public MemberDto selectByMember(long idx) {
        MemberDto selectMember = memberMapper.selectByMember(idx);

        if(selectMember == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }

        return selectMember;
    }

    @Transactional
    public MemberDto updateMember(long idx, MemberDto memberDto) {
        MemberDto selectMember = memberMapper.selectByMember(idx);

        if(selectMember == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }

        memberMapper.updateMember(selectMember);

        return memberMapper.selectByMember(idx);
    }

    @Transactional
    public int deleteMember(long idx){
        MemberDto selectMember = memberMapper.selectByMember(idx);

        if(selectMember == null){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }

        selectMember.setDeleteAt('Y');
        return memberMapper.updateMember(selectMember);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> selectByAllMember(){
        List<MemberDto> memberList = memberMapper.selectByAllMember();
        return memberList;
    }
}
