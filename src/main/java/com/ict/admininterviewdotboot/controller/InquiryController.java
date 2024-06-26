package com.ict.admininterviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.InquiryService;
import com.ict.admininterviewdotboot.vo.InquiryVO;

@RestController
@RequestMapping("/inquiry")
public class InquiryController {
    @Autowired
    private InquiryService inquiryService;
 
    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    @GetMapping("/inquirylist")
    public List<InquiryVO> getinquirylist() {
        return inquiryService.getinquirylist();
    }

    @GetMapping("/inquirydetail")
    public List<InquiryVO> getInquiryDetail(@RequestParam("i_idx") String i_idx) {
        return inquiryService.getInquiryDetail(i_idx);
    }

    @PostMapping("/mail2")
    public int sendMail(InquiryVO inquiryVO) {
        System.out.println("메일 전송 시작");
        inquiryService.sendSimpleMessage(inquiryVO);
        System.out.println("메일 전송 완료");
        return inquiryService.updatemail(inquiryVO);
    }
}
