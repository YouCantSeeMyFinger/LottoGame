package com.example.demo.controller;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.Member;
import com.example.demo.service.LoginFormService;
import com.example.demo.session.SessionManager;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginFormController {

    private final LoginFormService loginFormService;
    private final SessionManager sessionManager;

    @GetMapping("/member-login")
    public String loginMain(@ModelAttribute("loginForm") LoginForm loginForm) {
        return "/login/loginForm";
    }

    @PostMapping("/member-login")
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                log.info("errors => {}", fieldError);
            }
            return "/login/loginForm";
        }

        Member member = loginFormService.findById(loginForm.getLoginId(), loginForm.getLoginPassword());

        if (member == null) {
            bindingResult.reject("login Fail", "로그인 정보가 일치하지 않습니다.");
            return "/login/loginForm";
        }

        // 세션 매니저를 통한 쿠키 생성 및 세션 생성
        // 1 ) 쿠키 생성 Key = UUID , Value = Member
        // 2 ) Session 생성 Key = MySessionId , Value = UUID
        sessionManager.createSession(member, response);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
//        Cookie id = new Cookie("memberId", null);
//        id.setMaxAge(0);
//        response.addCookie(id);

        sessionManager.expire(request);
        return "redirect:/login";
    }
}
