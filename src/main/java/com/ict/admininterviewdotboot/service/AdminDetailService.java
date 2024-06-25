package com.ict.admininterviewdotboot.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.AdminMapper;
import com.ict.admininterviewdotboot.vo.AdminVO;

@Service
public class AdminDetailService implements UserDetailsService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        AdminVO admin = adminMapper.selectAdmin(id);
        System.out.println(id);
        if (admin == null) {
            throw new UsernameNotFoundException("User not found with username: " + id);
        }
        return new User(admin.getA_id(), admin.getA_pwd(), new ArrayList<>());
    }
    
}
