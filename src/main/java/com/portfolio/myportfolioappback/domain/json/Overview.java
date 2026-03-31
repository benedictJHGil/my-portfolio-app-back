package com.portfolio.myportfolioappback.domain.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class Overview {
    private String purpose;

    @JsonProperty("team_size")
    private String teamSize;

    @JsonProperty("main_features")
    private List<String> mainFeatures;
}
