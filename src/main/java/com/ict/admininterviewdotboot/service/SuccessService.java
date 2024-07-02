package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.SuccessMapper;
import com.ict.admininterviewdotboot.vo.SuccessVO;

@Service
public class SuccessService {
    @Autowired
    private SuccessMapper successMapper;

    public List<SuccessVO> getSuccessList(){
        return successMapper.getSuccessList();
    }

    public List<SuccessVO> getSuccessDetail(){
        return successMapper.getSuccessDetail();
    }

    public int deleteSuccess(SuccessVO successVO){
        return successMapper.deleteSuccess(successVO);
    } 
}
