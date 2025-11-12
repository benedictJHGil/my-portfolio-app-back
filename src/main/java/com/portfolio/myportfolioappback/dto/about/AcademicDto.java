package com.portfolio.myportfolioappback.dto.about;

public record AcademicDto(
    Long id,
    String name,
    String startdate,
    String enddate,
    String major,
    String grade
) {}
