package com.portfolio.myportfolioappback.dto;

import java.util.List;

public record ProjectDto(
    Long id,
    String title,
    String type,
    String startdate,
    String enddate,
    String git_rep_url,
    String page_url,
    List<SkillDto> dev_env,
    String image_url,
    String outline,
    String role,
    String content,
    String result
 ) {}
