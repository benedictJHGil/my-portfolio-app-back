package com.portfolio.myportfolioappback.dto;

public record ContactRequestDto(
    String title,
    String type,
    String contact,
    String message
) {}