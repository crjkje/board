package com.example.board.biz.dto;

import com.example.board.biz.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class ResponseDto {

    @Getter
    @AllArgsConstructor
    @Builder
    public static class MemberResponse {
        private String email;
        private String name;

        public static MemberResponse of(Member member) {
            return MemberResponse.builder()
                    .email(member.getEmail())
                    .name(member.getName())
                    .build();
        }
    }
}
