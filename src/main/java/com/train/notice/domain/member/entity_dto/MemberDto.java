package com.train.notice.domain.member.entity_dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberDto {

    private String username;
    private String name;
    private String password;
    private String email;

    public static MemberDto from (Member member){
        return MemberDto.builder()
                .username(member.getUsername())
                .name(member.getName())
                .password(member.getPassword())
                .email(member.getEmail())
                .build();
}
}
