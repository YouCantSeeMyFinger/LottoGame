package com.example.demo.session;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class SessionInfo {

    @GetMapping("session-info")
    public String sessionInfo(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session == null) {
            return "세션이 없습니다.";
        }

        session.getAttributeNames().asIterator().forEachRemaining(name -> log.info("name => {} , value => {}", name, session.getAttribute(name)));
        log.info("sessionId => {} ", session.getId());
        log.info("sessionMaxInactiveInterval => {} ", session.getMaxInactiveInterval());
        log.info("creationTime => {} ", new Date(session.getCreationTime()));
        log.info("sessiongetLastAccessedTime => {} ", new Date(session.getLastAccessedTime()));
        log.info("sessionisNew() => {} ", session.isNew());
        return "세션 출력완료";
    }
}
