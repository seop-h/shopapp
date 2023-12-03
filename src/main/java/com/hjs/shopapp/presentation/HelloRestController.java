package com.hjs.shopapp.presentation;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class HelloRestController {

    private final HelloService helloService;

    @GetMapping("/hello")
    public HelloDto getHello(@RequestParam("id") Long id) {
        return helloService.getHello(id);
    }

    @GetMapping("/hello/list")
    public List<HelloDto> getHelloList() {
        return helloService.getHelloList();
    }

    @PostMapping("/hello")
    public void register(@RequestBody HelloDto helloDto) {
        helloService.register(helloDto);
    }

    @PutMapping("/hello")
    public void updateHello(@RequestBody HelloDto helloDto) {
        helloService.updateHello(helloDto);
    }

    @DeleteMapping("/hello")
    public void deleteHello(@RequestParam("id") Long id) {
        helloService.deleteHello(id);
    }

}
