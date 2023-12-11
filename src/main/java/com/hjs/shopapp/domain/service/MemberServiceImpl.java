package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.MemberDto;
import com.hjs.shopapp.domain.model.Member;
import com.hjs.shopapp.infra.MemberSearchCond;
import com.hjs.shopapp.infra.member.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public MemberDto register(MemberDto memberDto) {
        memberRepository.findByUserId(memberDto.getUserId()).ifPresent(member -> {
            throw new IllegalArgumentException("해당 사용자 아이디가 이미 존재합니다.");
        });
        Member member = memberRepository.save(memberDto.toEntity());
        return member.toDto();
    }

    @Override
    public MemberDto getMember(Long id) {
        Optional<Member> foundMember = memberRepository.findById(id);
        if (foundMember.isEmpty()) {
            throw new IllegalArgumentException("아이디와 일치하는 회원정보가 없습니다.");
        }
        return foundMember.get().toDto();
    }

    @Override
    public MemberDto getMemberByUserId(String userId) {
        Optional<Member> foundMember = memberRepository.findByUserId(userId);
        if (foundMember.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디와 일치하는 회원정보가 없습니다.");
        }
        return foundMember.get().toDto();
    }

    @Override
    public List<MemberDto> getMemberList() {
        return memberRepository.findAll().stream()
                .map(Member::toDto)
                .toList();
    }

    @Override
    public List<MemberDto> getMemberList(MemberSearchCond cond) {
        String name = cond.getName();
        String userId = cond.getUserId();

        List<Member> memberList;

        if (StringUtils.hasText(name) && StringUtils.hasText(userId)) {
            memberList = memberRepository.findAllBy("%" + name + "%", "%" + userId + "%");
        } else if (StringUtils.hasText(name)) {
            memberList = memberRepository.findByNameLike("%" + name + "%");
        } else if (StringUtils.hasText(userId)) {
            memberList = memberRepository.findByUserIdLike("%" + userId + "%");
        } else {
            memberList = memberRepository.findAll();
        }

        return memberList.stream()
                .map(Member::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberDto updateMember(MemberDto memberDto) {
        Optional<Member> foundMember = memberRepository.findByUserId(memberDto.getUserId());
        if (foundMember.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디가 잘못되었습니다.");
        }

        Member member = foundMember.get();
        member.setName(memberDto.getName());
        member.setPhone(memberDto.getPhone());
        member.setAge(memberDto.getAge());
        member.setUserId(memberDto.getUserId());
        member.setPassword(memberDto.getPassword());

        return member.toDto();
    }

    @Override
    public void deleteMember(Long id) {
        Optional<Member> foundMember = memberRepository.findById(id);
        if (foundMember.isEmpty()) {
            throw new IllegalArgumentException("아이디와 일치하는 회원정보가 없습니다.");
        }
        memberRepository.delete(foundMember.get());
    }

    @Override
    public void deleteMemberByUserId(String userId) {
        Optional<Member> foundMember = memberRepository.findByUserId(userId);
        if (foundMember.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디와 일치하는 회원정보가 없습니다.");
        }
        memberRepository.delete(foundMember.get());
    }
}
