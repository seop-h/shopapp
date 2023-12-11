package com.hjs.shopapp.domain.model;

import com.hjs.shopapp.domain.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//TODO 어차피 userId가 중복되면 안되니까 이거를 primary key로 잡아도 되는지
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    private Integer age;

    private String userId;

    private String password;

    public MemberDto toDto() {
        return MemberDto.builder()
                .name(name)
                .phone(phone)
                .age(age)
                .userId(userId)
                .password(password)
                .build();
    }
}
