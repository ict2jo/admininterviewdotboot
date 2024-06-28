package com.ict.admininterviewdotboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.admininterviewdotboot.mapper.MemberMapper;
import com.ict.admininterviewdotboot.vo.GuestVO;
import java.util.List;

@Service
public class GuestService {
    @Autowired
    private MemberMapper memberMapper;

    public List<GuestVO> getGuestList(){
        return memberMapper.getGuestList();
    }
}
