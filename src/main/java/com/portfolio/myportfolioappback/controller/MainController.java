package com.portfolio.myportfolioappback.controller;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.myportfolioappback.domain.Project;
import com.portfolio.myportfolioappback.dto.MainPageResponse;
import com.portfolio.myportfolioappback.repository.ProjectRepository;
import com.portfolio.myportfolioappback.service.MainService;

@RestController
@RequestMapping("/api/main")
@RequiredArgsConstructor
public class MainController {

    private final ProjectRepository projectRepository;
    private final MainService mainService;
    
    @GetMapping("/{id}")
    public Optional<Project> getListById(@PathVariable Long id) {
        return projectRepository.findById(id);
    }
    
    @GetMapping("/full")
    public MainPageResponse getFull() {
        return mainService.getMainPage();
    }
}
