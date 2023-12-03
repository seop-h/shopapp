package com.hjs.shopapp.domain.dto;

import com.hjs.shopapp.domain.model.Hello;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HelloDto {

    private String name;
    private String phone;
    private Integer age;
    private String userId;
    private String password;

    public Hello toEntity() {
        return Hello.builder()
                .name(name)
                .phone(phone)
                .age(age)
                .userId(userId)
                .password(password)
                .build();
    }

}
