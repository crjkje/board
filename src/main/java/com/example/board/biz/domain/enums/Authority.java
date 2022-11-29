package com.example.board.biz.domain.enums;

public enum Authority {
    ROLE_USER("유저권한"), ROLE_ADMIN("관리자권한");
    
    private final String label;

    Authority(String label) {
        this.label = label;
    }
}
