package com.hjs.shopapp.infra;

import lombok.Data;

@Data
public class MemberSearchCond {

    private String name;
    private String userId;

    public MemberSearchCond() {
    }

    public MemberSearchCond(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }
}
