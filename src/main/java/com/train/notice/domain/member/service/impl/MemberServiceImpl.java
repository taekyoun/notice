package com.train.notice.domain.member.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.train.notice.domain.member.entity_dto.Member;
import com.train.notice.domain.member.entity_dto.MemberDto;
import com.train.notice.domain.member.repository.MemberRepository;
import com.train.notice.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void register(MemberDto dto) {
        if (memberRepository.existsById(dto.getUsername())) {
        throw new IllegalArgumentException("이미 사용 중인 아이디");
        }
        Member member =Member.create(dto.getUsername(), dto.getName(),passwordEncoder.encode(dto.getPassword()), dto.getEmail());
        memberRepository.save(member);
    }

    @Override
    public MemberDto findById(String id) {
        return MemberDto.from(memberRepository.findById(id).orElseThrow());
    }

    @Override
    public MemberDto updateMember(MemberDto dto) {
        Member member = memberRepository.findById(dto.getUsername()).orElseThrow();
        member.update(dto.getName(), passwordEncoder.encode(dto.getPassword()), dto.getEmail());
        return MemberDto.from(member);
    }


}
