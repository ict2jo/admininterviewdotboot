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
    //private OAuth2AuthenticaitonSuccessHandler oAuth2AuthenticaitonSuccessHandler;

    // 생성자를 통해 필요한 빈들을 주입받음
    public SecurityConfig(UserDetailsService userDetailsService,
                          JwtRequestFilter jwtRequestFilter
                          //OAuth2AuthenticaitonSuccessHandler oAuth2AuthenticaitonSuccessHandler
                          ) {
        this.userDetailsService = userDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
       // this.oAuth2AuthenticaitonSuccessHandler = oAuth2AuthenticaitonSuccessHandler;
    }

    // SecurityFilterChain 빈 설정
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf(csrf -> csrf.disable()) // CSRF 보안 기능 비활성화
        .cors(cors -> cors.configurationSource(corsConfigurationSource())) // CORS 설정 적용
        .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/**", "/review/**", "/inquiry/**", "/report/**", "/admin/**").permitAll() // 특정 URL 패턴에 대해 권한 없이 접근 허용
                        .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
        )
        .logout(logout -> logout
                        .logoutUrl("/api/logout") // 로그아웃 URL 설정
                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(200); // 로그아웃 성공 시 HTTP 응답 상태 코드 200으로 설정
                        })
        )
        // 먼저 토큰 검사 
        .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }    

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean    
     CorsConfigurationSource corsConfigurationSource() {
       CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    // CustomOAuth2userService 클래스는 사용자 정보를 가져오는 로직을 사용자 정의할 수 있는 클래스 
    // CustomOAuth2userService 클래스는 OAuth2UserService를 상속 받는 클래스이다.
    @Bean
    OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService(){
        return new CustomOAuth2userService();
    }
}
