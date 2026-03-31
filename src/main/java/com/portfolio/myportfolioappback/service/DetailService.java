package com.portfolio.myportfolioappback.service;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.myportfolioappback.domain.Project;
import com.portfolio.myportfolioappback.domain.ProjectDetail;
import com.portfolio.myportfolioappback.dto.ArchitectureDto;
import com.portfolio.myportfolioappback.dto.ProjectDetailResponse;
import com.portfolio.myportfolioappback.repository.ProjectDetailRepository;
import com.portfolio.myportfolioappback.repository.ProjectRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DetailService {

    private final ProjectRepository projectRepository;
    private final ProjectDetailRepository projectDetailRepository;

    public ProjectDetailResponse getProjectDetail(String slug) {

        Project project = projectRepository.findBySlug(slug).orElseThrow(() -> new RuntimeException("Project not found"));

        ProjectDetail detail = projectDetailRepository.findById(project.getId()).orElseThrow(() -> new RuntimeException("Detail not found"));

        return new ProjectDetailResponse(
                project.getTitle(),
                project.getType(),
                project.getImage_url(),
                project.getOutline(),
                detail.getOverview(),
                new ArchitectureDto(
                        detail.getArchitectureImageUrl(),
                        detail.getArchitectureDescription()
                ),
                detail.getCoreFeatures(),
                detail.getTechDecisions(),
                detail.getTroubleshooting(),
                detail.getPerformanceImprovements(),
                splitPipe(detail.getOperationsExperience()),
                splitPipe(detail.getRetrospective())
        );
    }
    
    private static List<String> splitPipe(String value) {
        if (value == null || value.isBlank()) return List.of();

        return Arrays.stream(value.split("\\|"))
            .map(String::trim)
            .toList();
    }
}
