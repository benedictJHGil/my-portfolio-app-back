package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100) private String name;
    @Column(nullable = false, length = 100) private String organization;
    @Column(nullable = false) private LocalDate date;
    private String level;
    private Integer score;
    @Column(nullable = false, length = 1) private String evaluate;

    protected Certificate() {}

    public Certificate(String name, String organization, LocalDate date, String level, Integer score, String evaluate) {
        this.name = name;
        this.organization = organization;
        this.date = date;
        this.level = level;
        this.score = score;
        this.evaluate = evaluate;
    }

    public static Certificate of(String name, String organization, LocalDate date, String level, Integer score, String evaluate) {
        return new Certificate(name, organization, date, level, score, evaluate);
    }
}
