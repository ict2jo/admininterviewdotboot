package com.ict.admininterviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.jwt.JWTUtil;
import com.ict.admininterviewdotboot.mapper.AdminMapper;
import com.ict.admininterviewdotboot.vo.AdminVO;
import com.ict.admininterviewdotboot.vo.DataVO;
import com.ict.admininterviewdotboot.vo.UserVO;

@Service
public class AuthService {
     
     @Autowired
     private AuthenticationManager authenticationManager;

     @Autowired
     private MyUserDetailsService userDetailsService;

     @Autowired
     private JWTUtil jwtUtil;

     @Autowired
     private AdminMapper adminMapper;

     @Autowired
     private PasswordEncoder passwordEncoder;

     public DataVO authenticate(AdminVO avo){
          DataVO dataVO = new DataVO();
          try {
               Authentication authentication = authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(avo.getA_id(), avo.getA_pwd()));

               // DB에서 사용자 정보 가져오기 
               UserVO uvo = userDetailsService.getUserDetail(avo.getA_id());
               String jwt = jwtUtil.generateToken(avo.getA_id());

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
     public int createUser(AdminVO avo){
          String encodedPw = passwordEncoder.encode(avo.getA_pwd());
          AdminVO newUser = new AdminVO();
          newUser.setA_pwd(encodedPw);
          newUser.setA_email(avo.getA_email());
          newUser.setA_id(avo.getA_id());
          newUser.setA_name(avo.getA_name());
          newUser.setA_phone(avo.getA_phone());
          int res = adminMapper.createUser(newUser);
          return res;
     }

}
