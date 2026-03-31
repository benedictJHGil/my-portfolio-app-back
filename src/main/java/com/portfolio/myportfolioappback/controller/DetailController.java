package com.portfolio.myportfolioappback.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portfolio.myportfolioappback.dto.ProjectDetailResponse;
import com.portfolio.myportfolioappback.service.DetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class DetailController {

    private final DetailService detailService;
    
    @GetMapping("/{slug}")
    public ProjectDetailResponse getProjectDetail(@PathVariable("slug") String slug) {
        return detailService.getProjectDetail(slug);
    }
}
