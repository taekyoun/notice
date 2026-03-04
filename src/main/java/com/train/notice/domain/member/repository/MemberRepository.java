package com.train.notice.domain.member.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.train.notice.domain.member.entity_dto.Member;

public interface MemberRepository extends JpaRepository<Member, String>{


}
