package com.hjs.shopapp.presentation.basic;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.service.HelloService;
import com.hjs.shopapp.infra.HelloSearchCond;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello(Model model, @RequestParam("userId") String userId) {
        HelloDto helloDto = helloService.getHelloByUserId(userId);
        model.addAttribute("helloDto", helloDto);

        return "hello";
    }

    @GetMapping("/list")
    public String list(@ModelAttribute("helloSearch") HelloSearchCond cond, Model model) {
        List<HelloDto> helloList = helloService.getHelloList(cond);
        model.addAttribute("helloList", helloList);
        return "list";
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String register(HelloDto helloDto, RedirectAttributes redirectAttributes) {
        HelloDto registered = helloService.register(helloDto);
        redirectAttributes.addAttribute("userId", registered.getUserId());
        return "redirect:/hello?userId={userId}";
    }

    @GetMapping("/update")
    public String updateForm(@RequestParam("userId") String userId, Model model) {
        HelloDto helloDto = helloService.getHelloByUserId(userId);
        model.addAttribute("helloDto", helloDto);
        return "update";
    }

    @PostMapping("/update")
    public String update(HelloDto helloDto, RedirectAttributes redirectAttributes) {
        HelloDto updated = helloService.updateHello(helloDto);
        redirectAttributes.addAttribute("userId", updated.getUserId());
        return "redirect:/hello?userId={userId}";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("userId") String userId, Model model) {
        HelloDto helloDto = helloService.getHelloByUserId(userId);
        model.addAttribute("helloDto", helloDto);
        return "delete";
    }

    @PostMapping("/delete")
    public String delete(HelloDto helloDto) {
        helloService.deleteHelloByUserId(helloDto.getUserId());
        return "redirect:/list";
    }

}
