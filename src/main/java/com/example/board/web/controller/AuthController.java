package com.example.board.web.controller;

import com.example.board.biz.dto.RequestDto;
import com.example.board.biz.dto.ResponseDto;
import com.example.board.biz.service.auth.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceImpl authService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDto.MemberResponse> signUp(@RequestBody RequestDto.SignUpRequest signUpRequest) {
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
}