package com.example.board.biz.dto;

import com.example.board.biz.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class ResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MemberResponse {
        @Email
        @NotBlank
        private String email;
        @NotBlank
        private String name;

        public static MemberResponse of(Member member) {
            return MemberResponse.builder()
                    .email(member.getEmail())
                    .name(member.getName())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @Builder
    public static class TokenResponse {
        private String grantType;
        private String accessToken;
        private String refreshToken;
        private Long tokenExpiresIn;
    }
}
