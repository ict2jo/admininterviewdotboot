package com.ict.admininterviewdotboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ict.admininterviewdotboot.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private UserDetailsService userDetailsService;
    private JwtRequestFilter jwtRequestFilter;
    private OAuth2AuthenticaitonSuccessHandler oAuth2AuthenticaitonSuccessHandler;

    // 생성자를 통해 필요한 빈들을 주입받음
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtRequestFilter jwtRequestFilter,
                          OAuth2AuthenticaitonSuccessHandler oAuth2AuthenticaitonSuccessHandler) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
        this.oAuth2AuthenticaitonSuccessHandler = oAuth2AuthenticaitonSuccessHandler;
    }

    // SecurityFilterChain 빈 설정
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable()) // CSRF 보안 기능 비활성화
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 적용
        .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**", "/review/**", "/inquiry/**", "/report/**").permitAll() // 특정 URL 패턴에 대해 권한 없이 접근 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
        )
        .logout(logout -> logout
                        .logoutUrl("/api/logout") // 로그아웃 URL 설정
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(200); // 로그아웃 성공 시 HTTP 응답 상태 코드 200으로 설정
                        })
        );
        
        // JWT 인증 필터를 UsernamePasswordAuthenticationFilter 앞에 추가
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build(); // 구성 완료된 HttpSecurity 객체 반환
    }

    // 비밀번호 인코더 빈 설정
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt 알고리즘을 사용하는 비밀번호 인코더 반환
    }    

    // AuthenticationManager 빈 설정
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager(); // 인증 관리자 빈 반환
    }

    // CORS 설정을 담당하는 CorsConfigurationSource 빈 설정
    @Bean    
    CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration config = new CorsConfiguration(); // CORS 설정 객체 생성
        config.setAllowCredentials(true); // 자격 증명 허용 설정
        config.addAllowedOriginPattern("*"); // 모든 오리진 허용
        config.addAllowedHeader("*"); // 모든 헤더 허용
        config.addAllowedMethod("*"); // 모든 HTTP 메서드 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // URL 기반 CorsConfigurationSource 객체 생성
        source.registerCorsConfiguration("/**", config); // 모든 경로에 대해 CORS 설정 적용

        return source; // 구성된 CorsConfigurationSource 객체 반환
    }

    // OAuth2User를 반환하는 사용자 서비스 빈 설정
    @Bean
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService() {
        return new CustomOAuth2userService(); // 사용자 정보를 가져오는 로직을 사용자 정의할 수 있는 OAuth2UserService 빈 반환
    }
}
