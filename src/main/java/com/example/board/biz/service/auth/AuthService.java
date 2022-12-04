package com.example.board.biz.service.auth;

import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;

public interface AuthService {
    void signUp(RequestDto.SignUpRequest signUp);
    ResponseDto.TokenResponse login(RequestDto.LoginRequest loginRequest);
}
