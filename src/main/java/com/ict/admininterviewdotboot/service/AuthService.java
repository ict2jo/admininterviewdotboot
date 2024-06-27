package com.ict.admininterviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.jwt.JWTUtil;
import com.ict.admininterviewdotboot.vo.DataVO;
import com.ict.admininterviewdotboot.vo.MembersVO;
import com.ict.admininterviewdotboot.vo.UserVO;

@Service
public class AuthService {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTUtil jwtUtil;

    public DataVO authenticate( MembersVO mvo){
       DataVO dataVO = new DataVO();
       try {
            Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(mvo.getA_id(), mvo.getA_pwd()));

            // DB에서 사용자 정보 가져오기 
            UserVO uvo = userDetailsService.getUserDetail(mvo.getA_id());
            String jwt = jwtUtil.generateToken(mvo.getA_id());

            // 리턴할 dataVO에 uvo, jwt 를 넣자 
            dataVO.setSuccess(true);
            dataVO.setToken(jwt);
            dataVO.setUserDetails(uvo);

            return dataVO;
       } catch (Exception e) {
            dataVO.setSuccess(false);
            dataVO.setMessage(e.getMessage());
            return dataVO ;
       }
    }
}
