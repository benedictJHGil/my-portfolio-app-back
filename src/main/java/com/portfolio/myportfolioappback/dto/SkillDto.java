package com.portfolio.myportfolioappback.dto;

public record SkillDto(
    Long id,
    String name,
    String type,
    String level,
    String category,
    String logo_url
) {}