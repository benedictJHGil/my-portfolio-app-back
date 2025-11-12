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

    protected Academic() {}

    public Academic(String name, LocalDate startdate, LocalDate enddate, String major, String grade) {
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.major = major;
        this.grade = grade;
    }

    public static Academic of(String name, LocalDate startdate, LocalDate enddate, String major, String grade) {
        return new Academic(name, startdate, enddate, major, grade);
    }
}
