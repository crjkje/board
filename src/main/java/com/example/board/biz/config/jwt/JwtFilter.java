package com.example.board.biz.config.jwt;

import com.example.board.biz.service.jwt.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    static String TOKEN_PREFIX = "Bearer ";
    static String HEADER_STRING = "Authorization";

    private final TokenProvider tokenProvider;
    private final RedisService redisService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Request Header 에서 토큰을 꺼냄
        String jwt = resolveToken(request);
        // 토큰 유효성 검사
        if (jwt != null && tokenProvider.validateToken(jwt, request)) {
            // 정상 토큰일 경우 해당 토큰으로 Authentication 을 가져와서 SecurityContext 에 저장

        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_STRING);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
