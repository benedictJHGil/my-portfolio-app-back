package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Career {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20) private String name;
    @Column(nullable = false) private LocalDate startdate;
    private LocalDate enddate;
    @Column(length = 100) private String reason;
    @Column(nullable = false, length = 10) private String department;
    @Column(name = "`rank`", nullable = false, length = 10) private String rank;
    @Column(nullable = false, length = 10) private String work;
    @Column(nullable = false, length = 20) private String pay;
    @Column(nullable = false, length = 20) private String location;
    @Column(nullable = false, length = 100) private String task;
    @Column(nullable = false, length = 50) private String dev_env;
    @Column(length = 1000) private String content;

    protected Career() {}

    public Career(String name, LocalDate startdate, LocalDate enddate, String reason, String department, String rank, String work, String pay, String location, String task, String dev_env, String content) {
        this.name = name;
        this.startdate = startdate;
        this.enddate = enddate;
        this.reason = reason;
        this.department = department;
        this.rank = rank;
        this.work = work;
        this.pay = pay;
        this.location = location;
        this.task = task;
        this.dev_env = dev_env;
        this.content = content;
    }

    public static Career of(String name, LocalDate startdate, LocalDate enddate, String reason, String department, String rank, String work, String pay, String location, String task, String dev_env, String content) {
        return new Career(name, startdate, enddate, reason, department, rank, work, pay, location, task, dev_env, content);
    }
}
