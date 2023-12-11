package com.hjs.shopapp.presentation.rest;

import com.hjs.shopapp.domain.dto.MemberDto;
import com.hjs.shopapp.domain.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberRestController {

    private final MemberService memberService;

    @GetMapping("/member")
    public MemberDto getMember(@RequestParam("id") Long id) {
        return memberService.getMember(id);
    }

    @GetMapping("/member/list")
    public List<MemberDto> getMemberList() {
        return memberService.getMemberList();
    }

    @PostMapping("/member")
    public void register(@RequestBody MemberDto memberDto) {
        memberService.register(memberDto);
    }

    @PutMapping("/member")
    public void updateMember(@RequestBody MemberDto memberDto) {
        memberService.updateMember(memberDto);
    }

    @DeleteMapping("/member")
    public void deleteMember(@RequestParam("id") Long id) {
        memberService.deleteMember(id);
    }

}
