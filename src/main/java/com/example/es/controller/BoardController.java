package com.example.es.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BoardController {

    @GetMapping("/board")
    public String main(Model model){

        model.addAttribute("test","게시판 테스트다 이말이야");

        return "board/main";
    }




}


