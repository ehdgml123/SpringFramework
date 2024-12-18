package com.zerock.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.zerock.domain.MemberVO;
import com.zerock.domin.CustomUser;
import com.zerock.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private MemberMapper memberMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.warn("------------" + username);
		
		MemberVO vo = memberMapper.read(username);
		
		log.warn("-----------------------"+ vo);
		
		return new CustomUser(vo);
	}
	
	
	
	}

	

