package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    MemberDto build;

    @BeforeEach
    void beforeEach() {
        build = MemberDto.builder().name("test1").phone("01012341234").age(12).userId("test").password("test").build();
        memberService.register(build);
    }

    @Test
    @DisplayName("회원가입 테스트")
    void register() {
        MemberDto hello = memberService.getMember(1L);
        Assertions.assertThat(build.getName()).isEqualTo(hello.getName());
    }

    @Test
    @DisplayName("회원조회 테스트")
    void getHello() {

    }
}