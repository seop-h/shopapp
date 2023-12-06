package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.model.Hello;
import com.hjs.shopapp.infra.HelloSearchCond;
import com.hjs.shopapp.infra.hello.HelloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HelloServiceImpl implements HelloService {

    private final HelloRepository helloRepository;

    @Override
    public HelloDto register(HelloDto helloDto) {
        helloRepository.findByUserId(helloDto.getUserId()).ifPresent(hello -> {
            throw new IllegalArgumentException("해당 사용자 아이디가 이미 존재합니다.");
        });
        Hello hello = helloRepository.save(helloDto.toEntity());
        return hello.toDto();
    }

    @Override
    public HelloDto getHello(Long id) {
        Optional<Hello> foundHello = helloRepository.findById(id);
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException("아이디와 일치하는 회원정보가 없습니다.");
        }
        return foundHello.get().toDto();
    }

    @Override
    public HelloDto getHelloByUserId(String userId) {
        Optional<Hello> foundHello = helloRepository.findByUserId(userId);
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디와 일치하는 회원정보가 없습니다.");
        }
        return foundHello.get().toDto();
    }

    @Override
    public List<HelloDto> getHelloList() {
        return helloRepository.findAll().stream()
                .map(Hello::toDto)
                .toList();
    }

    @Override
    public List<HelloDto> getHelloList(HelloSearchCond cond) {
        String name = cond.getName();
        String userId = cond.getUserId();

        List<Hello> helloList;

        if (StringUtils.hasText(name) && StringUtils.hasText(userId)) {
            helloList = helloRepository.findAllBy("%" + name + "%", "%" + userId + "%");
        } else if (StringUtils.hasText(name)) {
            helloList = helloRepository.findByNameLike("%" + name + "%");
        } else if (StringUtils.hasText(userId)) {
            helloList = helloRepository.findByUserIdLike("%" + userId + "%");
        } else {
            helloList = helloRepository.findAll();
        }

        return helloList.stream()
                .map(Hello::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public HelloDto updateHello(HelloDto helloDto) {
        Optional<Hello> foundHello = helloRepository.findByUserId(helloDto.getUserId());
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디가 잘못되었습니다.");
        }

        Hello hello = foundHello.get();
        hello.setName(helloDto.getName());
        hello.setPhone(helloDto.getPhone());
        hello.setAge(helloDto.getAge());
        hello.setUserId(helloDto.getUserId());
        hello.setPassword(helloDto.getPassword());

        return hello.toDto();
    }

    @Override
    public void deleteHello(Long id) {
        Optional<Hello> foundHello = helloRepository.findById(id);
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException("아이디와 일치하는 회원정보가 없습니다.");
        }
        helloRepository.delete(foundHello.get());
    }

    @Override
    public void deleteHelloByUserId(String userId) {
        Optional<Hello> foundHello = helloRepository.findByUserId(userId);
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException("사용자 아이디와 일치하는 회원정보가 없습니다.");
        }
        helloRepository.delete(foundHello.get());
    }
}
