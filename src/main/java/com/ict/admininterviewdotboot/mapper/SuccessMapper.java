package com.ict.admininterviewdotboot.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.SuccessVO;

@Mapper
public interface SuccessMapper {
    List<SuccessVO> getSuccessList();
    List<SuccessVO> getSuccessDetail();
    int deleteSuccess(SuccessVO successVO);
}
