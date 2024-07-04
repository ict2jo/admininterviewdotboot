package com.ict.admininterviewdotboot.service;

import com.ict.admininterviewdotboot.mapper.MemberMapper;
import com.ict.admininterviewdotboot.vo.UserVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private MemberMapper memberMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO member = memberMapper.selectMember(username);
        if (member == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(member.getA_id(), member.getPassword(), new ArrayList<>());
    }

    // DB에서 개인정보를 추출하는 메서드
    // /api/login, /api/userInfo 엔드포인트에서 호출됨
    public UserVO getUserDetail(String id) throws Exception {
        // 사용자 ID를 기반으로 데이터베이스에서 사용자 정보를 조회
        UserVO uvo = memberMapper.selectMember(id);
        return uvo;
    }

    // OAuth2 사용자 정보를 기반으로 사용자를 로드하는 메서드
    // public UserDetails loadUserByOAuth2User(OAuth2User oAuth2User) {
    //     // OAuth2 사용자로부터 이메일, 이름, 전화번호 등의 정보 추출
    //     String a_email = oAuth2User.getAttribute("a_email");
    //     String a_name = oAuth2User.getAttribute("a_name");
    //     String a_id = ""; // 사용자 ID 초기화

    //     // 해당 이메일로 DB에 사용자 정보가 있는지 확인
    //     UserVO uvo = memberMapper.findUserByEmail(a_email);

    //     if (uvo == null) {
    //         // DB에 사용자 정보가 없는 경우: 신규 사용자 등록(insert)
    //         uvo = new UserVO();
    //         uvo.setA_id(a_id); // 사용자 ID 설정
    //         uvo.setA_email(a_email); // 이메일 설정
    //         uvo.setA_name(a_name != null ? a_name : "hong"); // 이름 설정 (기본값: "hong")
    //         memberMapper.insertUser(uvo); // 사용자 정보를 데이터베이스에 삽입
    //     } else {
    //         // DB에 사용자 정보가 있는 경우: 사용자 정보 업데이트(update)
    //         memberMapper.updateUser(uvo); // 기존 사용자 정보를 업데이트
    //     }

    //     // Spring Security UserDetails 객체를 생성하여 반환
    //     // 여기서는 사용자명(ID)만 설정하고, 비밀번호는 비어있는 상태로 반환함
    //     return new User(uvo.getA_id(), "", new ArrayList<>());
    // }
}
