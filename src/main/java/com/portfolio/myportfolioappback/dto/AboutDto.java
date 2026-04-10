package com.portfolio.myportfolioappback.dto;

import com.portfolio.myportfolioappback.domain.json.Introduction;

public record AboutDto(
    Long id,
    String nameKr,
    String nameEn,
    String nickname,
    String birthdate,
    String phoneNumber,
    String email,
    String job,
    Introduction introduction,
    String resume,
    String github,
    String blog,
    String youtube
) {}
