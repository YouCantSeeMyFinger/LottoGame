package com.example.demo.web.filter;

import com.example.demo.session.SessionConst;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/login", "/member-login", "/", "/member-add", "/css/*", "/logout"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("로그인채크필터 시작");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();
        String uuid = UUID.randomUUID().toString();

        try {
            if (this.checkLogin(requestURI)) {
                HttpSession session = httpServletRequest.getSession(false);

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
                    httpServletResponse.sendRedirect("/member-login?requestURI=" + requestURI);
                    log.info("{}", requestURI);
                    return;
                }
            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            throw e;
        }
        log.info("로그인체크필터 끝");
    }

    public boolean checkLogin(String requestURI) {
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);
    }
}




