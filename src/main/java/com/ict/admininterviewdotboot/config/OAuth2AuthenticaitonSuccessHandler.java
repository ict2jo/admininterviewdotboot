package com.ict.admininterviewdotboot.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.ict.admininterviewdotboot.jwt.JWTUtil;
import com.ict.admininterviewdotboot.service.MyUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// OAuth2 로그인 성공 시 처리하는 핸들러 
@Component
public class OAuth2AuthenticaitonSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JWTUtil jwtUtil;
    private final MyUserDetailsService userDetailsService;

    // 생성자를 통해 JWTUtil과 MyUserDetailsService를 주입받음
    public OAuth2AuthenticaitonSuccessHandler(JWTUtil jwtUtil, MyUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    // 인증 성공 후 처리하는 메서드
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        try {
            // OAuth2User 객체를 가져옴 (인증된 사용자 정보)
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            // 요청에서 제공자(provider)를 추출함
            //String provider = getProviderFromRequest(request);

            // 디버깅 로그
            System.out.println("OAuth2 User: " + oAuth2User);

            // OAuth2User 정보를 기반으로 UserDetails를 가져옴
            UserDetails userDetails = userDetailsService.loadUserByOAuth2User(oAuth2User);

            // UserDetails로 JWT 토큰 생성
            String token = jwtUtil.generateToken(userDetails);

            // HTTP 응답 헤더에 JWT 토큰 추가
            response.addHeader("Authorization", "Bearer " + token);

            // 클라이언트로 리다이렉트하여 토큰을 전달
            response.sendRedirect("http://localhost:3000?token=" + token);
        } catch (Exception e) {
            e.printStackTrace();
            // 예외 발생 시 로그인 페이지로 리다이렉트
            response.sendRedirect("/login?error");
        }
    }

    // // 요청에서 제공자(provider)를 추출하는 메서드
    // public String getProviderFromRequest(HttpServletRequest request) {
    //     String uri = request.getRequestURI();
    //     if (uri.contains("kakao")) {
    //         return "kakao";
    //     } else if (uri.contains("naver")) {
    //         return "naver";
    //     } else if (uri.contains("google")) {
    //         return "google";
    //     } else {
    //         return "unknown";
    //     }
    // }
}
