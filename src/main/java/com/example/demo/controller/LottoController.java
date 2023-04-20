package com.example.demo.controller;

import com.example.demo.domain.LottoDTO;
import com.example.demo.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
        log.info("lottoDTO => {}", lottoDTO);
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
        lottoService.reset();
        return "redirect:/";
    }

    @PostMapping("/show")
    public String show(Model model) {
        model.addAttribute("values", lottoService.findAll());
        return "/show";
    }


} //End class
