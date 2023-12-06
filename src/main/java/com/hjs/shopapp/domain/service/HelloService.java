package com.hjs.shopapp.domain.service;

import com.hjs.shopapp.domain.dto.HelloDto;
import com.hjs.shopapp.infra.HelloSearchCond;

import java.util.List;

public interface HelloService {

    HelloDto register(HelloDto helloDto);

    HelloDto getHello(Long id);

    HelloDto getHelloByUserId(String userId);

    List<HelloDto> getHelloList();

    List<HelloDto> getHelloList(HelloSearchCond cond);

    HelloDto updateHello(HelloDto helloDto);

    void deleteHello(Long id);

    void deleteHelloByUserId(String userId);
}
