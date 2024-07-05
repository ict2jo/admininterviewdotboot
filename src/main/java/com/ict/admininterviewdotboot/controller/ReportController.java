package com.ict.admininterviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.ReportService;
import com.ict.admininterviewdotboot.vo.InquiryVO;
import com.ict.admininterviewdotboot.vo.ReportVO;
import com.ict.admininterviewdotboot.vo.UsersVO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private ReportService reportService;
 
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/reportlist")
    public List<ReportVO> getreportlist() {
        return reportService.getreportlist();
    }
    
    @GetMapping("/reportdetail")
    public List<ReportVO> getReportDetail(@RequestParam("rep_idx") String rep_idx) {
        return reportService.getReportDetail(rep_idx);
    }
    
    @PostMapping("/reportclick")
    public int getreportclick(@RequestBody ReportVO rvo) {
        System.out.println(rvo.getA_id());
        System.out.println(rvo.getU_idx());
        return reportService.getreportclick(rvo);
    }
    @GetMapping("/userlist")
    public List<UsersVO> getuserlist() {
        return reportService.getuserlist();
    }
    @PostMapping("/userdelete")
    public int getuserdelete(UsersVO uvo) {
        return reportService.getuserdelete(uvo);
    }
    @PostMapping("/userlive")
    public int getuserlive(UsersVO uvo) {
        return reportService.getuserlive(uvo);
    }
}
