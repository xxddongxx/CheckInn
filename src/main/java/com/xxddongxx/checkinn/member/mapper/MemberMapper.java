package com.xxddongxx.checkinn.member.mapper;

import com.xxddongxx.checkinn.member.model.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    int insertMember(Member member);
    Member loadUsrByUsername(String userId);
    boolean isDuplicate(String userId);
    boolean isOwner(String ownerId);
    Member selectByMember(long idx);
    int updateMember(Member member);
    List<Member> selectByAllMember();
}
