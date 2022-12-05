package com.example.board.biz.service.auth;

import com.example.board.biz.config.jwt.TokenProvider;
import com.example.board.biz.domain.auth.CryptUtils;
import com.example.board.biz.domain.member.Member;
import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;
import com.example.board.biz.repository.MemberRepository;
import com.example.board.biz.service.jwt.RedisService;
import com.example.board.web.exception.ExistingMemberException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl{
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final RedisService redisService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CryptUtils cryptUtils;

    @Transactional
    public void signUp(RequestDto.SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(cryptUtils.encryptEmail(signUpRequest.getEmail()))) {
            throw new ExistingMemberException();
        }
        Member member = signUpRequest.toEntity(passwordEncoder, cryptUtils);
        ResponseDto.MemberResponse.of(memberRepository.save(member));
    }

    public ResponseDto.TokenResponse login(RequestDto.LoginRequest loginRequest) {
        // Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = loginRequest.toAuthentication();
        // authenticate 메서드가 실행될 때 principalDetailsService 의 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 인증 저오를 기반으로 JWT 토큰 생성
        ResponseDto.TokenResponse tokenResponseDto = tokenProvider.createTokenDto(authentication);

        // refresh 토큰 저장
        redisService.setValues(loginRequest.getEmail(),
                tokenResponseDto.getRefreshToken(),
                Duration.ofMillis(1000 * 60 * 24 * 7));
        return tokenResponseDto;

    }
}
