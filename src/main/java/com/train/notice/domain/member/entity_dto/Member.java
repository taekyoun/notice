package com.train.notice.domain.member.entity_dto;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "notice_member")
public class Member {

    @Id
    @Column(name = "id")
    private String username;

    @Column
    private String name;

    @Column
    private String password;

    @Column
    private String email;

     public static Member create(String username,String name,String password,String email){
        Member member = new Member();
        member.username = username;
        member.name = name;
        member.password = password;
        member.email = email;
        return member;
    }

    public void update(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }
}
