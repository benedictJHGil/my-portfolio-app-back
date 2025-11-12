package com.portfolio.myportfolioappback.dto.about;

public record AboutDto(
    Long id,
    String nameKr,
    String nameEn,
    String nickname,
    String birthdate,
    String phoneNumber,
    String email,
    String github,
    String blog,
    String youtube
) {}
