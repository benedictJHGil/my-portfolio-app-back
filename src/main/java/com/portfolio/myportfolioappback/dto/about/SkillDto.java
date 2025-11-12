package com.portfolio.myportfolioappback.dto.about;

public record SkillDto(
    Long id,
    String name,
    String type,
    String level,
    String logo_url
) {}