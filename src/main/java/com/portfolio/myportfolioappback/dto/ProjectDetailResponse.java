package com.portfolio.myportfolioappback.dto;

import java.util.List;

import com.portfolio.myportfolioappback.domain.json.*;

public record ProjectDetailResponse(
    String title,
    String type,
    String imageUrl,
    String outline,
    Overview overview,
    ArchitectureDto architecture,
    List<CoreFeature> coreFeatures,
    List<TechDecisions> techDecisions,
    List<Troubleshooting> troubleshooting,
    List<PerformanceImprovement> performanceImprovements,
    List<String> operationsExperience,
    List<String> retrospective
) {}