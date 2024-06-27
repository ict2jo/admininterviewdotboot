package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.MemberMapper;
import com.ict.admininterviewdotboot.vo.AdminVO;
import com.ict.admininterviewdotboot.vo.MembersVO;

@Service
public class AdminService {
     @Autowired
    private MemberMapper memberMapper;

    public List<MembersVO> getAdminList(){
        return memberMapper.getAdminList();
    }

    public ResponseEntity<?> authenticate(AdminVO admin) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'authenticate'");
    }

}
