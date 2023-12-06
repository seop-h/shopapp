package com.hjs.shopapp.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdviceSample {

    // 특정 예외 처리
    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    public ResponseEntity<Object> handleCustomException() {
        // 예외 처리 로직
        return new ResponseEntity<>("arument error", HttpStatus.BAD_REQUEST);
    }


}

