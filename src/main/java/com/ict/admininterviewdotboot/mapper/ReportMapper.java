package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.ReportVO;

@Mapper
public interface ReportMapper {
    List<ReportVO> getreportlist();
    List<ReportVO> getReportDetail(String rep_idx);
    int getreportclick1(String u_idx);
    int getreportclick2(String u_idx);
    int getreportclick3(String u_idx);
}
