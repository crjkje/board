package com.example.board.biz.dto;

import com.example.board.biz.domain.auth.CryptUtils;
import com.example.board.biz.domain.enums.Authority;
import com.example.board.biz.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;

public class RequestDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class SignUpRequest {
        @NotBlank(message = "이메일을 입력하세요.")
        private String email;
        @NotBlank(message = "비밀번호를 입력하세요")
        private String password;
        @NotBlank(message = "이름을 입력하세요")
        private String name;

        public Member toEntity(PasswordEncoder passwordEncoder, CryptUtils encryptEmail) {
            return Member.builder()
                    .email(encryptEmail.encryptEmail(email))
                    .password(passwordEncoder.encode(password))
                    .name(name)
                    .authority(Authority.ROLE_USER)
                    .build();
        }
    }
}
