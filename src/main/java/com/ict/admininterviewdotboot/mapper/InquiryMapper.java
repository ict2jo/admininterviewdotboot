package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.InquiryVO;

@Mapper
public interface InquiryMapper {
    List<InquiryVO> getinquirylist();
    List<InquiryVO> getInquiryDetail(String i_idx);
    int updatemail(InquiryVO inquiryVO);
}
