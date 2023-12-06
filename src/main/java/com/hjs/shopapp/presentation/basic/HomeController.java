package com.hjs.shopapp.presentation.basic;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.service.HelloService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final HelloService helloService;

    @RequestMapping("/")
    public String home() {
        return "redirect:/list";
    }

    @PostConstruct
    void init() {
        HelloDto helloDto = HelloDto.builder()
                .name("hjs")
                .phone("01066977430")
                .age(27)
                .userId("hwangjs")
                .password("123")
                .build();
        helloService.register(helloDto);
    }

}
