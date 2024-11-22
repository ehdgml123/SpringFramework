package com.zerock.domin;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.zerock.domain.MemberVO;

import lombok.Getter;

@Getter

public class CustomUser extends User {

    private MemberVO member;

    public CustomUser(MemberVO vo) {
        super(vo.getUserName(), vo.getUserpw(), 
              vo.getAuthList().stream()
                .map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
                .collect(Collectors.toList()));
        this.member = vo;
    }

    public MemberVO getMember() {
        return member;
    }
}
