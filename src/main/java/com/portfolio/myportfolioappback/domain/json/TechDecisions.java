package com.portfolio.myportfolioappback.domain.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class TechDecisions {
    @JsonProperty("tech_name")
    private String techName;

    private List<String> alternatives;

    @JsonProperty("decision_reason")
    private String decisionReason;

    @JsonProperty("trade_off")
    private String tradeOff;
    private String result;
}
