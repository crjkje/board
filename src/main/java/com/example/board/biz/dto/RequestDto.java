package com.example.board.biz.dto;

import com.example.board.biz.domain.enums.Authority;
import com.example.board.biz.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class SignUpRequest {
        private String email;
        private String password;
        private String name;

        public Member toEntity(PasswordEncoder passwordEncoder) {
            return Member.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .authority(Authority.ROLE_USER)
                    .build();
        }
    }
}
