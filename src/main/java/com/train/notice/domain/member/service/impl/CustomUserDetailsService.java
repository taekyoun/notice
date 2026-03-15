package com.train.notice.domain.member.service.impl;


import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.train.notice.domain.member.entity_dto.Member;
import com.train.notice.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findById(username).orElseThrow(()-> new UsernameNotFoundException("사용자를 찾을 수 없습니다"));
        return new User(member.getUsername(), member.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));


    }

}
