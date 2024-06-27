package com.ict.admininterviewdotboot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ict.admininterviewdotboot.vo.GuestVO;
import com.ict.admininterviewdotboot.vo.MembersVO;
import com.ict.admininterviewdotboot.vo.UserVO;

import java.util.List;

@Mapper
public interface MemberMapper {
    UserVO selectMember(@Param("a_id") String a_id) ;
    List<GuestVO> getGuestList();
    List<MembersVO> getAdminList();

    UserVO findUserByEmail(@Param("a_email") String a_email);
    void insertUser(UserVO uvo);
    void updateUser(UserVO uvo);
}
