package com.example.demo.controller;

import com.example.demo.argumentresolver.Login;
import com.example.demo.domain.Member;
import com.example.demo.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/home")
    public String home(@Login Member loginMember, Model model) {

        if (loginMember == null) {
            return "/login/loginForm";
        }
        model.addAttribute("member", loginMember);
        return "/login/loginhome";
    }

}
