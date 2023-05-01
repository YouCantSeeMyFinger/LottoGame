package com.example.demo.repository.member;


import com.example.demo.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@Slf4j
public class MemberRepository implements IMemberRepo {
    private final static Map<Integer, Member> memberRepo = new HashMap<>();
    private static Integer sequence = 0;


    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        memberRepo.put(member.getId(), member);
        log.info("memberRepo => {}", memberRepo);
        return member;
    }

    @Override
    public Member findById(Integer memberId) {
        return memberRepo.get(memberId);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberRepo.values());
    }

    @Override
    public Optional<Member> findLoginId(String loginId) {
        return this.findAll()
                .stream()
                .filter(member -> member.getLoginId().equals(loginId))
                .findFirst();
    }

    @Override
    public boolean duplicatedMember(Member member) {
        return this.findAll().stream()
                .anyMatch(m -> m.getLoginId().equals(member.getLoginId()));
    }

    @Override
    public void reset() {
        memberRepo.clear();
    }
}
