package com.ict.admininterviewdotboot.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ict.admininterviewdotboot.vo.AdminVO;


@Mapper
public interface AdminMapper {
    List<AdminVO> getadminlist();
    List<AdminVO> getAdminDetail(String a_idx);
    int editadmin(AdminVO adminVO);
    int admindelete(AdminVO adminVO);
    int adminlive(AdminVO adminVO);
    int createUser(AdminVO adminVO);
    AdminVO getUserId(String id);
}

