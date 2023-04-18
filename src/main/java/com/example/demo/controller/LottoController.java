package com.example.demo.controller;

import com.example.demo.domain.LottoDTO;
import com.example.demo.service.LottoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String save(@ModelAttribute("lottoDTO") LottoDTO lottoDTO) {
        log.info("LottoDTO => {}", lottoDTO);
        lottoService.save(lottoDTO);
        return "redirect:/";
    }

    @GetMapping("/reset")
    public String reset() {
        lottoService.reset();
        return "redirect:/";
    }


} //End class
