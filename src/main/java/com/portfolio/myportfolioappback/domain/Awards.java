package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Awards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200) private String name;
    @Column(nullable = false) private LocalDate date;

    protected Awards() {}

    public Awards(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public static Awards of(String name, LocalDate date) {
        return new Awards(name, date);
    }
}
