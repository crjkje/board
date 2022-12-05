package com.example.board.biz.service.auth;

import com.example.board.biz.domain.auth.CryptUtils;
import com.example.board.biz.domain.member.Member;
import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.repository.MemberRepository;
import com.example.board.web.exception.ExistingMemberException;
import com.example.board.web.exception.MemberNotFound;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class AuthServiceImplTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AuthServiceImpl authService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CryptUtils cryptUtils;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Nested
    @DisplayName("회원가입 테스트")
    class SingUpTest {
        @Nested
        @DisplayName("성공 사례")
        class successCase {
            @Test
            @DisplayName("저장된 이메일 확인")
            void test1() {

                // given
                String email = "test@test.com";
                RequestDto.SignUpRequest testUser = RequestDto.SignUpRequest.builder()
                        .email(email)
                        .password(passwordEncoder.encode("1234"))
                        .name("jk")
                        .build();

                // when
                authService.signUp(testUser);
                String encryptEmail = cryptUtils.encryptEmail(email);
                Member member = memberRepository.findByEmail(encryptEmail).orElseThrow(()->new MemberNotFound());

                // then
                Assertions.assertThat(member.getEmail()).isEqualTo(encryptEmail);
                Assertions.assertThat(member.getName()).isEqualTo("jk");
            }
        }

        @Nested
        @DisplayName("실패 사례")
        class failCase {
            @Test
            @DisplayName("중복계정 체크")
            void test1() {
                // given
                String email = "test@test.com";
                RequestDto.SignUpRequest user1 = RequestDto.SignUpRequest.builder()
                        .email(email)
                        .password(passwordEncoder.encode("1234"))
                        .name("jk")
                        .build();
                RequestDto.SignUpRequest user2 = RequestDto.SignUpRequest.builder()
                        .email(email)
                        .password(passwordEncoder.encode("1234"))
                        .name("jk")
                        .build();
                // when
                authService.signUp(user1);

                // then
                Assertions.assertThatThrownBy(() -> authService.signUp(user2), "예외처리가 잘못 되었습니다.").isInstanceOf(ExistingMemberException.class);
            }
        }
    }

    @Nested
    @DisplayName("로그인 테스트")
    class loginTest {
        @Nested
        @DisplayName("성공 사례")
        class successCase {

        }

        @Nested
        @DisplayName("실패 사례")
        class failCase {

        }

    }

}