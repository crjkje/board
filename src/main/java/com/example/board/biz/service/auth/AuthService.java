package com.example.board.biz.service.auth;

import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;

public interface AuthService {
    ResponseDto.MemberResponse signUp(RequestDto.SignUpRequest signUp);
}
