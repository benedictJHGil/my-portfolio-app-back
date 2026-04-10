package com.portfolio.myportfolioappback.domain.json;

import java.util.List;

import lombok.Getter;

@Getter
public class Introduction {
    private String intro;
    private List<String> highlights;
    private List<String> summary;
}
