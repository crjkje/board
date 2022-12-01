package com.example.board.biz.service.auth;

import com.example.board.biz.domain.auth.CryptUtils;
import com.example.board.biz.domain.member.Member;
import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;
import com.example.board.biz.repository.MemberRepository;
import com.example.board.web.exception.ExistingMemberException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final CryptUtils cryptUtils;

    @Override
    @Transactional
    public void signUp(RequestDto.SignUpRequest signUpRequest) {

        if (memberRepository.existsByEmail(cryptUtils.encryptEmail(signUpRequest.getEmail()))) {
            throw new ExistingMemberException();
        }
        Member member = signUpRequest.toEntity(passwordEncoder, cryptUtils);
        ResponseDto.MemberResponse.of(memberRepository.save(member));
    }
}
