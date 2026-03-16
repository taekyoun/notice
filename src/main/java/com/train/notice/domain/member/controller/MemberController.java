package com.train.notice.domain.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.train.notice.domain.member.entity_dto.MemberDto;
import com.train.notice.domain.member.service.MemberService;
import com.train.notice.jwt.JwtTokenProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    

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

    @PostMapping("/login")
    public String doLogin(@ModelAttribute MemberDto dto, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String accessToken = jwtTokenProvider.createToken(userDetails);
        Cookie cookie = new Cookie("accessToken", accessToken);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);


        return "redirect:/";
    }
    
    @PostMapping("/logout")
    public String doLogout(HttpServletResponse response) {
        Cookie cookie = new Cookie("accessToken", null);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return "redirect:/";
    }
    


}
