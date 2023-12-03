package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.domain.model.Hello;
import com.hjs.shopapp.infra.hello.HelloRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class HelloServiceImpl implements HelloService {

    private final HelloRepository helloRepository;

    @Override
    public void register(HelloDto helloDto) {
        helloRepository.findByUserId(helloDto.getUserId()).ifPresent(hello -> {
            throw new IllegalArgumentException();
        });
        helloRepository.save(helloDto.toEntity());
    }

    @Override
    public HelloDto getHello(Long id) {
        return helloRepository.findById(id).get().toDto();
    }

    @Override
    public List<HelloDto> getHelloList() {
        return helloRepository.findAll().stream()
                .map(Hello::toDto)
                .toList();
    }

    @Override
    public void deleteHello(Long id) {
        Optional<Hello> foundHello = helloRepository.findById(id);
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException();
        }
        helloRepository.delete(foundHello.get());
    }

    @Override
    public void updateHello(HelloDto helloDto) {
        Optional<Hello> foundHello = helloRepository.findByUserId(helloDto.getUserId());
        if (foundHello.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Hello hello = foundHello.get();
        hello.setName(helloDto.getName());
        hello.setPhone(helloDto.getPhone());
        hello.setAge(helloDto.getAge());
        hello.setUserId(helloDto.getUserId());
        hello.setPassword(helloDto.getPassword());
    }
}
