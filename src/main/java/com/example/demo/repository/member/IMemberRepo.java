package com.example.demo.repository.member;

import com.example.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface IMemberRepo {


    Member save(Member member);

    Member findById(Integer memberId);

    List<Member> findAll();

    Optional<Member> findLoginId(String loginId);

    void reset();
}
