package com.ict.admininterviewdotboot.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

// UserDetails : Spring Security를 거친 사용자 정보들 
@Data
public class UserVO implements UserDetails{
    private String a_id = "";
    private String a_pwd = "";
    private String a_email = "";
    private String a_name = "";
    private int a_status = 0;
    
    private List<GrantedAuthority> authorities = new ArrayList<>();
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {       return authorities;    }
    @Override
    public String getPassword() {          return a_pwd;    }
    @Override
    public String getUsername() {        return a_id;    }
    @Override
    public boolean isAccountNonExpired() {        return true;    }
    @Override
    public boolean isAccountNonLocked() {        return true;    }
    @Override
    public boolean isCredentialsNonExpired() {        return true;    }
    @Override
    public boolean isEnabled() {        return true;    }
    
}
