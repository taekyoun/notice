package com.train.notice.domain.member.service;

import com.train.notice.domain.member.entity_dto.MemberDto;

public interface MemberService {

    void register(MemberDto dto);         

    MemberDto findById(String id);                 

    MemberDto updateMember(MemberDto dto);
    
}
