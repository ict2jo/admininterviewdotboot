package com.ict.admininterviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.jwt.JWTUtil;
import com.ict.admininterviewdotboot.jwt.JwtResponse;
import com.ict.admininterviewdotboot.vo.AdminVO;

@Service
public class AdminService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public ResponseEntity<?> authenticate(AdminVO admin) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(admin.getA_id(), admin.getA_pwd()));
            System.out.println("authentication" + authentication);
            final UserDetails userDetails = userDetailsService.loadUserByUsername(admin.getA_id());
            final String jwt = jwtUtil.generateToken(userDetails);
            System.out.println(jwt);
            return ResponseEntity.ok(new JwtResponse(jwt));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed : " + e.getMessage());
        }
    } 
}
