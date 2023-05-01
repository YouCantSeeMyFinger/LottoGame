package com.example.demo.validator;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MemberValidator implements Validator {

    private final MemberRepository memberRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Member.class.isAssignableFrom(clazz);
        //Member == clazz
        //subMember == clazz
    }

    @Override
    public void validate(Object target, Errors errors) {
        Member member = (Member) target;

        if (memberRepository.duplicatedMember(member)) {
            errors.rejectValue("loginId", "duplicated loginId", new Object[]{member.getLoginId()}, "사용중인 아이디입니다.");
        }

    }
}
