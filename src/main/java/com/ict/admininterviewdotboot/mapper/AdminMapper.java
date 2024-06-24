package com.ict.admininterviewdotboot.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.admininterviewdotboot.vo.AdminVO;


@Mapper
public interface AdminMapper {
    AdminVO selectAdmin(@Param("a_id") String a_id);
}
