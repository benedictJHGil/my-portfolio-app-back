package com.portfolio.myportfolioappback.dto;

import java.util.List;

public record ExperienceDto (
    Long id,
    String title,
    String type,
    String period,
    String role,
    String summary,
    String company,
    List<String> dev_env
) {}
