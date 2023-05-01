package com.example.demo.controller;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.Member;
import com.example.demo.service.LoginFormService;
import jakarta.servlet.http.Cookie;
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

        Cookie idCookie = new Cookie("memberId", String.valueOf(member.getId()));
        response.addCookie(idCookie);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie id = new Cookie("memberId", null);
        id.setMaxAge(0);
        response.addCookie(id);
        return "redirect:/login";
    }
}
