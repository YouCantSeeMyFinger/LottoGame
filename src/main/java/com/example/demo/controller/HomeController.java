package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;


    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {

        Member member = (Member) sessionManager.findSession(request);

        if (member == null) {
            return "/login/loginForm";
        }
        model.addAttribute("member", member);
        return "/login/loginhome";
    }

}
