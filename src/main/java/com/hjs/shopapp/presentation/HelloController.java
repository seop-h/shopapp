package com.hjs.shopapp.presentation;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.service.HelloService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

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

    @GetMapping("/hello")
    public String hello(Model model) {

        HelloDto hello = helloService.getHello(1L);
        model.addAttribute("name", hello.getName());
        model.addAttribute("userId", hello.getUserId());
        model.addAttribute("password", hello.getPassword());

        return "hello";
    }

    @GetMapping("/reigster")
    public String register() {
        return "register";
    }

    @GetMapping("/list")
    public String list() {
        return "list";
    }

    @GetMapping("/delete")
    public String delete() {
        return "delete";
    }

}
