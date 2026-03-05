package com.portfolio.myportfolioappback.dto;

import java.util.List;

public record MainPageResponse(
    List<ProjectDto> portfolio,
    List<ProjectDto> personalProjects,
    List<ProjectDto> workProjects
) {}
