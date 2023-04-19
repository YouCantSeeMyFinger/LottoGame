package com.example.demo.controller;

import com.example.demo.domain.LottoDTO;
import com.example.demo.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        // log.info("lottoDTO => {}", lottoDTO);
        return "/mainview/main";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("lottoDTO") LottoDTO lottoDTO, RedirectAttributes redirectAttributes) {
        // log.info("LottoDTO => {}", lottoDTO);
        redirectAttributes.addFlashAttribute("lottoDTO", lottoDTO);
        lottoService.save(lottoDTO);
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
