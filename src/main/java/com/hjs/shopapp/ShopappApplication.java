package com.hjs.shopapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class ShopappApplication {

/*    @org.springframework.web.bind.annotation.RestController
    class RestController {
        @GetMapping("/")
        public String index() {
            return "Hello, World";
        }
    }*/

    public static void main(String[] args) {
        SpringApplication.run(ShopappApplication.class, args);
    }

}
