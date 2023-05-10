package com.example.demo.argumentresolver;

import com.example.demo.domain.Member;
import com.example.demo.session.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("supportsParameter :: invoked");
        boolean hasLoginAnnotation = parameter.hasParameterAnnotation(Login.class);
        // 컨트롤러의 Parameter에 @Login이라는 어노테이션이 붙었는지 확인

        boolean isMemberClass = Member.class.isAssignableFrom(parameter.getParameterType());
        // Member Type 확인

        return hasLoginAnnotation && isMemberClass;
        // 두 가지 조건을 만족하면 true를 반환한다.
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.info("resolveArgument :: invoked");
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        HttpSession session = request.getSession(false);

        if (session == null) {
            return null;
        }
        return session.getAttribute(SessionConst.LOGIN_MEMBER);

    }
}
