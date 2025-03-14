package com.xxddongxx.checkinn.member.mapper;

import com.xxddongxx.checkinn.member.dto.MemberDto;
import com.xxddongxx.checkinn.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    int insertMember(Member member);
    Member selectByMember(String userId);
    boolean isDuplicate(String userId);
    boolean isOwner(String ownerId);
}
