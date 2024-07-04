package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.AdminMapper;
import com.ict.admininterviewdotboot.vo.AdminVO;
import com.ict.admininterviewdotboot.vo.UserVO;

@Service
public class AdminService {
     @Autowired
    private AdminMapper adminMapper;

        @Autowired
        private PasswordEncoder passwordEncoder;


    public ResponseEntity<?> authenticate(AdminVO admin) {
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    public List<AdminVO> getadminlist(){
        return adminMapper.getadminlist();
    }
    public List<AdminVO> getAdminDetail(String a_idx){
        return adminMapper.getAdminDetail(a_idx);
    }
    public int editadmin(AdminVO avo){
        String encodedPw = passwordEncoder.encode(avo.getA_pwd());
        AdminVO newUser = new AdminVO();
        newUser.setA_pwd(encodedPw);
        newUser.setA_email(avo.getA_email());
        newUser.setA_id(avo.getA_id());
        newUser.setA_name(avo.getA_name());
        newUser.setA_phone(avo.getA_phone());
        int res = adminMapper.editadmin(newUser);
        return res;
   }


    public int admindelete(AdminVO adminVO){
        return adminMapper.admindelete(adminVO);
    }
    public int adminlive(AdminVO adminVO){
        return adminMapper.adminlive(adminVO);
    }
    public AdminVO getUserId(String a_id) {
    return adminMapper.getUserId(a_id);
  }
}
