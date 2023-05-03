package com.example.demo.session;

import com.example.demo.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import static org.assertj.core.api.Assertions.assertThat;


class SessionManagerTest {

    @Test
    void sessionTest() {
        SessionManager sessionManager = new SessionManager();

        // 세션생성
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        Member member = new Member();
        sessionManager.createSession(member, mockHttpServletResponse);

        // 요청에 응답쿠키 저장
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
        mockHttpServletRequest.setCookies(mockHttpServletResponse.getCookies());

        // 세션 조회
        Object result = sessionManager.findSession(mockHttpServletRequest);
        assertThat(result).isEqualTo(member);

        sessionManager.expire(mockHttpServletRequest);
        Object expired = sessionManager.findSession(mockHttpServletRequest);
        assertThat(expired).isNull();
    }

}