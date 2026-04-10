package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Academic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) private String name;
    @Column(nullable = false) private LocalDate startdate;
    private LocalDate enddate;
    @Column(nullable = false, length = 100) private String major;
    @Column(length = 5) private String grade;
    @Column(name = "is_final", nullable = false, length = 5) 
    private boolean isFinal;

    protected Academic() {}
}
