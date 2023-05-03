package com.example.demo.session;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class SessionManager {

    public static final String MY_SESSION_ID = "mySessionId";
    private Map<String, Object> sessions = new ConcurrentHashMap<>();

    /**
     * 세션 생성 <br>
     * sessionId 생성 (임의의 추정 불가능한 랜던 값) <br>
     * 세션 저장소에 sessionId와 보관할 값 저장 <br>
     * 세션 ID를 사용하여 응답 쿠키를 생성 후에 클라이언트에 전달 <br>
     */


    /**
     * 세션 생성
     *
     * @param value
     * @param response
     */
    public void createSession(Object value, HttpServletResponse response) {

        // UUID를 이용한 세션 키 생성 후 저장
        String uuid = UUID.randomUUID().toString();
        sessions.put(uuid, value);

        // 응답으로로 쿠키를 생성
        // 쿠키에 UUID를 Value로 저장
        Cookie cookie = new Cookie(MY_SESSION_ID, uuid);
        response.addCookie(cookie);
    }

    /**
     * 세션 조회 메소드<br>
     * 1 ) 세션을 조회을 조회하기 전에 우선 쿠키를 찾아야한다. <br>
     * 2 ) 찾은 쿠키의 value는 세션 테이블의 key 값이다. <br>
     * 3 ) key값을 이용해 session 테이블의 value => Object를 찾는다. <br>
     *
     * @param request <br>
     * @Return Object
     */

    public Object findSession(HttpServletRequest request) {
        Cookie cookie = this.findCookie(request, MY_SESSION_ID);
        if (cookie == null) {
            return null;
        }
        return sessions.get(cookie.getValue());
    }

    /**
     * 쿠키 조회
     *
     * @param request
     * @param cookieName
     * @return Cookie
     */
    public Cookie findCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(cookieName))
                .findAny()
                .orElse(null);
    }

    public void expire(HttpServletRequest request) {
        Cookie cookie = findCookie(request, MY_SESSION_ID);
        if (cookie != null) {
            sessions.remove(cookie.getValue());
        }
    }

}
