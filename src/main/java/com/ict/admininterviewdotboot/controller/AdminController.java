package com.ict.admininterviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.AdminService;
import com.ict.admininterviewdotboot.vo.AdminVO;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/api")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/adminlogin")
    public ResponseEntity<?> loginAdmin(@RequestBody AdminVO admin) {
        System.out.println("here");
        return adminService.authenticate(admin);
    }

    
}
