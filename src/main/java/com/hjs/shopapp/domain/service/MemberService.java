package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.MemberDto;
import com.hjs.shopapp.infra.MemberSearchCond;

import java.util.List;

public interface MemberService {

    MemberDto register(MemberDto memberDto);

    MemberDto getMember(Long id);

    MemberDto getMemberByUserId(String userId);

    List<MemberDto> getMemberList();

    List<MemberDto> getMemberList(MemberSearchCond cond);

    MemberDto updateMember(MemberDto memberDto);

    void deleteMember(Long id);

    void deleteMemberByUserId(String userId);
}
