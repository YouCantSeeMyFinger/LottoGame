package com.example.demo.controller;

import com.example.demo.domain.LoginForm;
import com.example.demo.domain.Member;
import com.example.demo.service.LoginFormService;
import com.example.demo.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
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
    public String login(@Validated @ModelAttribute("loginForm") LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request) {

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
        // HttpServletRequest API에 이미 정의 되어있음
        // 참고로 true 값을 주는 이유는 없는 경우는 만들어야 하기 때문에 false 경우는 없으면 null 반환한다.
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);
        log.info("session => {}", session);
        return "redirect:/home";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        // false의 이유 => 세션의 삭제가 목표이기 때문에 만약 해당 세션이 없다면 굳이 만들어줄 필요가 없다.
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }

        return "redirect:/login";
    }
}
