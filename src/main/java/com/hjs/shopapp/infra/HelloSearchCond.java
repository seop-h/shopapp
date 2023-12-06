package com.hjs.shopapp.infra;

import lombok.Data;

@Data
public class HelloSearchCond {

    private String name;
    private String userId;

    public HelloSearchCond() {
    }

    public HelloSearchCond(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }
}
