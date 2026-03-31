package com.portfolio.myportfolioappback.dto;

import com.portfolio.myportfolioappback.domain.json.ArchitectureDescription;

public record ArchitectureDto(
    String imageUrl,
    ArchitectureDescription description
) {}
