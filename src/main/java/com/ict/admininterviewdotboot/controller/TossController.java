package com.ict.admininterviewdotboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.TossService;
import com.ict.admininterviewdotboot.vo.InterviewVO;
import com.ict.admininterviewdotboot.vo.TossVO;
import com.ict.admininterviewdotboot.vo.UsersVO;

@RestController
@RequestMapping("/payments")
public class TossController {

    @Autowired
    private TossService tossService;

    @GetMapping("/payList")
    public List<TossVO> payList() {
        return tossService.payList();
    }

    @PostMapping("/cancel")
    public ResponseEntity<TossVO> cancelPayment(@RequestBody TossVO tvo, @RequestHeader("Authorization") String authorizationHeader) {
        try {
            System.out.println("티아이디"+tvo.getT_idx());
            System.out.println("페이먼츠키"+tvo.getPaymentKey());
            System.out.println("사유사유사유"+tvo.getCancelReason());
            System.out.println("z키키키키ㅣ키"+authorizationHeader);
            System.out.println("에이아이디"+tvo.getA_id());
            boolean isCanceled = tossService.cancelPayment(
                tvo.getPaymentKey(),
                tvo.getT_idx(),
                authorizationHeader,
                tvo.getCancelReason(),
                tvo.getA_id()
                );
            if (isCanceled) {
                return ResponseEntity.ok(new TossVO());
            } else {
                return ResponseEntity.status(400).body(new TossVO());
            }
        } catch (Exception e) {
            System.out.println("컨트롤러" + e );
        }
        return null;
    }

    @GetMapping("/userList")
    public List<UsersVO> userList() {
        return tossService.userList();
    }

    @GetMapping("/interviewList")
    public List<InterviewVO> interviewList() {
        return tossService.interviewList();
    }
}
