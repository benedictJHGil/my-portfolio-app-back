package com.portfolio.myportfolioappback.dto;

public record AcademicDto(
    Long id,
    String name,
    String startdate,
    String enddate,
    String major,
    String grade
) {}
