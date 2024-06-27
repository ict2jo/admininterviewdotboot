package com.ict.admininterviewdotboot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ict.admininterviewdotboot.mapper.ReportMapper;
import com.ict.admininterviewdotboot.vo.ReportVO;
@Service
public class ReportService {
    @Autowired
    private ReportMapper reportMapper;
    
    public List<ReportVO> getreportlist(){
        return reportMapper.getreportlist();
    }
    
    public List<ReportVO> getReportDetail(String rep_idx){
        return reportMapper.getReportDetail(rep_idx);
    }

    @Transactional
        public int getreportclick(String u_idx) {
            int result1 = reportMapper.getreportclick1(u_idx);
            int result2 = reportMapper.getreportclick2(u_idx);
            int result3 = reportMapper.getreportclick3(u_idx);

            // 모든 업데이트가 성공했는지 확인
            if (result1 > 0 && result2 > 0 && result3 > 0) {
                return 1;
            } else {
                throw new RuntimeException("업데이트 중 오류 발생");
            }
        }

}
