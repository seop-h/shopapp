package com.hjs.shopapp.domain.dto;

import com.hjs.shopapp.domain.model.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotNull
    @Range(min = 0, max = 150)
    private Integer age;

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .phone(phone)
                .age(age)
                .userId(userId)
                .password(password)
                .build();
    }

}
