package com.example.demo.testdatainit;


import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class TestDataInit {
    private final MemberRepository memberRepository;


    @PostConstruct
    public void init() {
        memberRepository.save(new Member("sin", "Qwe1320568!", "신원균1"));
        memberRepository.save(new Member("won", "Qwe1320568!", "신원균2"));
    }
}
