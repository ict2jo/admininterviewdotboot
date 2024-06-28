package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.ReportVO;

@Mapper
public interface ReportMapper {
    List<ReportVO> getreportlist();
    List<ReportVO> getReportDetail(String rep_idx);
    int getreportclick1(ReportVO rvo);
    int getreportclick2(ReportVO rvo);
    int getreportclick3(ReportVO rvo);
    int getreportclick4(ReportVO rvo);
}
