package com.portfolio.myportfolioappback.dto.about;

import java.util.List;

public record CareerDto(
    Long id,
    String name,
    String startdate,
    String enddate,
    String duration,
    String reason,
    String department,
    String rank,
    String work,
    String pay,
    String location,
    String task,
    List<String> dev_env,
    String content
) {}
