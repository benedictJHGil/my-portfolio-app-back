package com.portfolio.myportfolioappback.domain.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

@Getter
public class CoreFeature {

    @JsonProperty("feature_name")
    private String featureName;
    private String description;
    private List<String> implementation;

    @JsonProperty("tech_stack")
    private List<String> techStack;
}
