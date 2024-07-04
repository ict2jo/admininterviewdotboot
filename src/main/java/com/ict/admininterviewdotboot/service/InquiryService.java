package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.InquiryMapper;
import com.ict.admininterviewdotboot.vo.InquiryVO;
@Service
public class InquiryService {
    @Autowired
    private InquiryMapper inquiryMapper;
    @Autowired
    private JavaMailSender emailSender;
    
    public List<InquiryVO> getinquirylist(){
        return inquiryMapper.getinquirylist();
    }
    
    public List<InquiryVO> getInquiryDetail(String i_idx){
        return inquiryMapper.getInquiryDetail(i_idx);
    }

    public void sendSimpleMessage(InquiryVO inquiryVO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("ppee1220@gmail.com");
        message.setTo(inquiryVO.getEmail());
        message.setSubject(inquiryVO.getTitle());
        message.setText(inquiryVO.getContent());
        emailSender.send(message);
    }

    public int updatemail(InquiryVO inquiryVO){
        return inquiryMapper.updatemail(inquiryVO);
    }


}
