package com.example.board.biz.service.auth;

import com.example.board.biz.domain.member.Member;
import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;
import com.example.board.biz.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public ResponseDto.MemberResponse signUp(RequestDto.SignUpRequest signUpRequest) {
        if (memberRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }
        Member member = signUpRequest.toEntity(passwordEncoder);
        return ResponseDto.MemberResponse.of(memberRepository.save(member));
    }
}
