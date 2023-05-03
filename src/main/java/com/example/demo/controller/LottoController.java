package com.example.demo.controller;

import com.example.demo.domain.Lotto;
import com.example.demo.dto.lotto.LottoDTO;
import com.example.demo.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
public class LottoController {

    private final LottoService lottoService;

    /**
     * 메인화면
     */
    @GetMapping("/")
    public String mainView(@ModelAttribute("lottoDTO") LottoDTO lottoDTO) {
        return "/mainview/main";
    }

    @PostMapping("/save")
    public String save(@Validated @ModelAttribute("lottoDTO") LottoDTO lottoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.info("ErrorType => {}", bindingResult);
            return "/mainview/main";
        }
        lottoService.save(lottoDTO);
        redirectAttributes.addFlashAttribute("lottoDTO", lottoDTO);
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String reset() {
        log.info("reset invoked :: method");
        lottoService.reset();
        return "redirect:/";
    }

    @PostMapping("/show")
    public String show(Model model) {
        log.info("invoked show:: method");
        log.info("findAll => {} ", lottoService.findAll());
        model.addAttribute("values", lottoService.findAll());
        return "/show";
    }

    @GetMapping("/lotto-key/{status}")
    public String findId(@PathVariable Integer status, Model model) {
        Lotto id = lottoService.findId(status);

        log.info("PathVariable => {} ", status);
        log.info("findId => {}", id);
        model.addAttribute("lottoObject", id);
        return "/show-single-lotto";
    }

    @GetMapping("/login")
    public String login() {
        return "/login/login";
    }


} //End class
