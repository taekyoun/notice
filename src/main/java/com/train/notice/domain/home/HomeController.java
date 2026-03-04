package com.train.notice.domain.home;

import org.springframework.web.bind.annotation.GetMapping;

public class HomeController {
  @GetMapping("/")
    public String welcomeHome() {


        return "index";
    }
  
}
