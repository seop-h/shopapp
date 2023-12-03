package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.HelloDto;

import java.util.List;

public interface HelloService {

    void register(HelloDto helloDto);

    HelloDto getHello(Long id);

    List<HelloDto> getHelloList();

    void deleteHello(Long id);

    void updateHello(HelloDto helloDto);
}
