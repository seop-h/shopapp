package com.hjs.shopapp.presentation.basic;

import com.hjs.shopapp.domain.dto.MemberDto;
import com.hjs.shopapp.domain.service.MemberService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/member/list";
    }

    @PostConstruct
    void init() {
        MemberDto memberDto = MemberDto.builder()
                .name("hjs")
                .phone("01066977430")
                .age(27)
                .userId("hwangjs")
                .password("123")
                .build();
        memberService.register(memberDto);
    }

}
