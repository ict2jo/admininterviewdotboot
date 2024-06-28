package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.AdminMapper;
import com.ict.admininterviewdotboot.vo.AdminVO;

@Service
public class AdminService {
     @Autowired
    private AdminMapper adminMapper;

    public ResponseEntity<?> authenticate(AdminVO admin) {
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

    public List<AdminVO> getadminlist(){
        return adminMapper.getadminlist();
    }
    public List<AdminVO> getAdminDetail(String a_idx){
        return adminMapper.getAdminDetail(a_idx);
    }
    public int editadmin(AdminVO adminVO){
        return adminMapper.editadmin(adminVO);
    }
    public int admindelete(AdminVO adminVO){
        return adminMapper.admindelete(adminVO);
    }

}
