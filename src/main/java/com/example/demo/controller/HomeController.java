package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.session.SessionConst;
import com.example.demo.session.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
@RequiredArgsConstructor
public class HomeController {


    @GetMapping("/home")
    public String home(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            return "/login/loginForm";
        }

        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);
        if (loginMember == null) {
            return "/login/loginForm";
        }
        model.addAttribute("member", loginMember);
        return "/login/loginhome";
    }

}
