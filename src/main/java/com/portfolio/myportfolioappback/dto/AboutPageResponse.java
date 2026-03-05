package com.portfolio.myportfolioappback.dto;

import java.util.List;

public record AboutPageResponse(
    AboutDto profile,
    List<SkillDto> skills,
    String totalDate,
    List<CareerDto> careers,
    List<AcademicDto> academics,
    List<CertificateDto> certificates
) {}