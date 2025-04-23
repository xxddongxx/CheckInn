package com.xxddongxx.checkinn.member.mapper;

import com.xxddongxx.checkinn.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(MemberDto member);
    MemberDto loadUsrByUsername(String userId);
    boolean isDuplicate(String userId);
    boolean isOwner(String ownerId);
    MemberDto selectByMember(Long idx);
    int updateMember(MemberDto member);
    List<MemberDto> selectByAllMember();
}
