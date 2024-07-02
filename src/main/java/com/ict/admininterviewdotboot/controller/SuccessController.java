package com.ict.admininterviewdotboot.controller;

import org.springframework.web.bind.annotation.RestController;

import com.ict.admininterviewdotboot.service.SuccessService;
import com.ict.admininterviewdotboot.vo.SuccessVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/success")
public class SuccessController {
    
    @Autowired
    private SuccessService successService;

    @GetMapping("/successlist")
    public List<SuccessVO> getSuccessList() {
        return successService.getSuccessList();
    }
    
    @PostMapping("/successdetail")
    public List<SuccessVO> getSuccessDetail() {
        return successService.getSuccessDetail();
    }

    @PostMapping("/deletesuccess")
    public int deleteSuccess(@RequestBody SuccessVO successVO) {
        successVO.setActive("1");
        return successService.deleteSuccess(successVO);
    }
}
