package com.train.notice.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.train.notice.domain.member.entity_dto.MemberDto;
import com.train.notice.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;


@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/login")
    public String goLogin() {
        return "login";
    }


    @GetMapping("/signup")
    public String goSignUp() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> doSignUp(@ModelAttribute MemberDto dto, Model model) {
        memberService.register(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


}
