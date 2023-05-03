package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginFormService {
    private final MemberRepository memberRepository;

    public Member findById(String loginId, String loginPassword) {
        return memberRepository.findLoginId(loginId)
                .filter(member -> member.getLoginPassword().equals(loginPassword))
                .orElse(null);
    }

}
