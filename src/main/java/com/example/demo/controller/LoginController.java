package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.naming.Binding;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final MemberRepository memberRepository;

    @GetMapping("/member-add")
    public String add(@ModelAttribute("member") Member member) {
        log.info("add invoked");
        return "/member/addmember";
    }

    @PostMapping("/member-add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/member/addmember";
        }

        log.info("member => {}", member);
        memberRepository.save(member);
        return "redirect:/";
    }
}
