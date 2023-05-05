package com.example.demo.controller.membership;

import com.example.demo.domain.Member;
import com.example.demo.repository.member.MemberRepository;
import com.example.demo.validator.member.MemberValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MembershipController {

    private final MemberRepository memberRepository;
    private final MemberValidator memberValidator;

    @GetMapping("/member-add")
    public String add(@ModelAttribute("member") Member member) {
        return "/member/addmember";
    }

    @PostMapping("/member-add")
    public String save(@Validated @ModelAttribute("member") Member member, BindingResult bindingResult) {

        memberValidator.validate(member, bindingResult);

        if (bindingResult.hasErrors()) {
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                log.info("error => {} ", error);
            }
            return "/member/addmember";
        }
        memberRepository.save(member);
        return "redirect:/";
    }
}
