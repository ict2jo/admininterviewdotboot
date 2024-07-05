package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.InterviewVO;
import com.ict.admininterviewdotboot.vo.TossVO;
import com.ict.admininterviewdotboot.vo.UsersVO;

@Mapper
public interface TossMapper {
    List<TossVO> payList();
    int cancelPayment(TossVO tvo);
    int userPayCount2(TossVO tvo);
    List<UsersVO> userList();
    List<InterviewVO> interviewList();
}
