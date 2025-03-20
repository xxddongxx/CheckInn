package com.xxddongxx.checkinn.member.service;

import com.xxddongxx.checkinn.config.exception.CustomException;
import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.mapper.MemberMapper;
import com.xxddongxx.checkinn.member.model.Member;
import com.xxddongxx.checkinn.member.model.MemberDetails;
import com.xxddongxx.checkinn.member.model.Role;
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
        Optional<Member> member = Optional.ofNullable(memberMapper.loadUsrByUsername(userId));

        if (!member.isPresent()){
            throw new IllegalArgumentException("User not authorized.");
        }
        return new MemberDetails(member.get());
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


        return memberMapper.insertMember(new Member().toEntity(memberDto));
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
        Optional<Member> selectMember = Optional.ofNullable(memberMapper.selectByMember(idx));

        if(selectMember.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }

        return new MemberDto().toDto(selectMember.get());
    }

    @Transactional
    public MemberDto updateMember(long idx, MemberDto memberDto) {
        Optional<Member> selectMember = Optional.ofNullable(memberMapper.selectByMember(idx));

        if(selectMember.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }

        Member updateTarget = selectMember.get();
        try {
            updateTarget.setNm(memberDto.getNm());
            updateTarget.setRole(Role.valueOf(memberDto.getRole().toString()));
            updateTarget.setEmail(memberDto.getEmail());
            updateTarget.setPhone(memberDto.getPhone());
            updateTarget.setDeleteAt(memberDto.getDeleteAt());
        } catch (CustomException e){
            throw new CustomException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다. 다시 확인해주세요.");
        }

        memberMapper.updateMember(updateTarget);

        Optional<Member> resultMember = Optional.ofNullable(memberMapper.selectByMember(idx));
        return new MemberDto().toDto(resultMember.get());
    }

    @Transactional
    public int deleteMember(long idx){
        Optional<Member> selectMember = Optional.ofNullable(memberMapper.selectByMember(idx));

        if(selectMember.isEmpty()){
            throw new CustomException(HttpStatus.NOT_FOUND, "해당 유적를 찾을 수 없습니다.");
        }
        Member deleteMember = selectMember.get();
        deleteMember.setDeleteAt('Y');
        return memberMapper.updateMember(deleteMember);
    }

    @Transactional(readOnly = true)
    public List<MemberDto> selectByAllMember(){
        List<Member> memberList = memberMapper.selectByAllMember();

        List<MemberDto> memberDtoList = memberList.stream().map( obj -> new MemberDto().toDto(obj)).toList();
        return memberDtoList;
    }
}
