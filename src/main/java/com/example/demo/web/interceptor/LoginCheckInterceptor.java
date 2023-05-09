package com.example.demo.web.interceptor;

import com.example.demo.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * preHandle => Handler가 실행 되기 전 <br>
 * postHandler => Handler가 실행 되고 해당되는 controller를 반환 후 <br>
 * afterCompletion => 관련 View를 반환하고 나서 *반드시* 실행되는 메소드 <br>
 * <p>
 * 위의 이유로 인해 로그인 관련 필터 , 인터셉터 처리는 preHandle만 필요 <br>
 * 참고로 default가 붙은 인터페이스의 메소드는 구현해도 그만 안해도 그만이다. <br>
 */

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("로그인체크 인터셉터 시작");

        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null) {
            log.info("미인증 사용자 요청");
            response.sendRedirect("/member-login?redirect=" + uri);
            return false;
        }

        log.info("인증된 사용자 요청");
        log.info("로그인체크 인터셉터 끝");
        return true;
    }
}
