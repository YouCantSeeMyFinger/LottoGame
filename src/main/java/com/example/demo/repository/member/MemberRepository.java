package com.example.demo.repository.member;


import com.example.demo.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MemberRepository implements IMemberRepo {
    private final static Map<Integer, Member> memberRepo = new HashMap<>();
    private static Integer sequence = 0;



}
