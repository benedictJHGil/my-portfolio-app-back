package com.portfolio.myportfolioappback.dto.about;

import java.util.List;

public record AboutPageResponse(
    AboutDto profile,
    List<SkillDto> skills,
    String totalDate,
    List<CareerDto> careers,
    List<AcademicDto> academics,
    List<CertificateDto> certificates
) {}