package com.portfolio.myportfolioappback.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.myportfolioappback.domain.About;
import com.portfolio.myportfolioappback.dto.about.AboutPageResponse;
import com.portfolio.myportfolioappback.repository.AboutRepository;
import com.portfolio.myportfolioappback.service.AboutService;

@RestController
@RequestMapping("/api/about")
@RequiredArgsConstructor
public class AboutController {

    private final AboutRepository aboutRepository;
    private final AboutService aboutService;

    @GetMapping("/{id}")
    public Optional<About> getListById(@PathVariable Long id) {
        return aboutRepository.findAllById(id);
    }

    @GetMapping("/full")
    public AboutPageResponse getFull() {
        return aboutService.getAboutPage();
    }
}
