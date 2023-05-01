package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;

    @GetMapping("/home")
    public String home(@CookieValue(name = "memberId", required = false) Integer memberId, Model model) {
        if (memberId == null) {
            return "/login/loginForm";
        }
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "/login/loginForm";
        }
        model.addAttribute("member", loginMember);
        return "/login/loginhome";
    }

}
