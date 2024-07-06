package com.ict.admininterviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.AdminService;
import com.ict.admininterviewdotboot.vo.AdminVO;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
 
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/adminlist")
    public List<AdminVO> getadminlist() {
        return adminService.getadminlist();
    }
    @GetMapping("/admindetail")
    public List<AdminVO> getAdminDetail(@RequestParam("a_idx") String a_idx) {
        return adminService.getAdminDetail(a_idx);
    }
    @PostMapping("/adminedit")
    public  ResponseEntity<?> editAdmin(@RequestBody AdminVO avo) {
        int res = adminService.editadmin(avo);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/admindelete")
    public int admindelete(AdminVO adminVO) {
        return adminService.admindelete(adminVO);
    }
    @PostMapping("/adminlive")
    public int adminlive(AdminVO adminVO) {
        return adminService.adminlive(adminVO);
    }
    
}
