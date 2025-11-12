package com.portfolio.myportfolioappback.dto.about;

public record CertificateDto(
    Long id,
    String name,
    String organization,
    String date,
    String level,
    Integer score,
    String evaluate
) {}