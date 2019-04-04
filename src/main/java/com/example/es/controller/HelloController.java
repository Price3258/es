package com.example.es.controller;


import com.example.es.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }


    @GetMapping("/")
    public String main(Model model) {

        model.addAttribute("test","테스트다 이말이야");

        Member member = new Member();
        member.setUid("22");
        member.setName("wtkim");
        member.setPassword("23123");

        model.addAttribute("member",member);

        return "main";
    }


}
