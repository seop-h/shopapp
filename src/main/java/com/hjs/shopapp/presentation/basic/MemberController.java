package com.hjs.shopapp.presentation.basic;

import com.hjs.shopapp.domain.dto.MemberDto;
import com.hjs.shopapp.domain.service.MemberService;
import com.hjs.shopapp.infra.MemberSearchCond;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public String hello(Model model, @RequestParam("userId") String userId) {
        MemberDto memberDto = memberService.getMemberByUserId(userId);
        model.addAttribute("memberDto", memberDto);

        return "member";
    }

    //TODO 검색 결과가 없으면 "검색 결과 없음"을 표시
    @GetMapping("/list")
    public String list(@ModelAttribute("memberSearch") MemberSearchCond cond, Model model) {
        List<MemberDto> memberList = memberService.getMemberList(cond);
        model.addAttribute("memberList", memberList);
        return "list";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("memberDto", new MemberDto());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute MemberDto memberDto, BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {

        /*
        TODO 에러 코드가 typeMismatch.memberDto.age인 경우와 Range.memberDto.age인 경우 모두 에러 메시지를
            "나이는 0 이상 150이하의 숫자여야 합니다."로 통일하기
         */
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "register";
        }
        MemberDto registered = memberService.register(memberDto);
        redirectAttributes.addAttribute("userId", registered.getUserId());
        return "redirect:/member?userId={userId}";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("userId") String userId, Model model) {
        MemberDto memberDto = memberService.getMemberByUserId(userId);
        model.addAttribute("memberDto", memberDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(@Validated @ModelAttribute MemberDto memberDto, BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("errors={}", bindingResult);
            return "update";
        }
        MemberDto updated = memberService.updateMember(memberDto);
        redirectAttributes.addAttribute("userId", updated.getUserId());
        return "redirect:/member?userId={userId}";
    }

    @PostMapping("/delete/{userId}")
    public String delete(@PathVariable("userId") String userId) {
        memberService.deleteMemberByUserId(userId);
        return "redirect:/member/list";
    }

}
