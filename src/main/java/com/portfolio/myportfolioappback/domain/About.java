package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.portfolio.myportfolioappback.domain.json.Introduction;

@Getter
@Entity
public class About {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10) private String name_kr;
    @Column(nullable = false, length = 20) private String name_en;
    @Column(length = 20) private String nickname;
    @Column(nullable = false) private LocalDate birthdate;
    @Column(nullable = false, length = 13) private String phoneNumber;
    @Column(nullable = false, length = 30) private String email;
    @Column(nullable = false, length = 30) private String job;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json", nullable = false, length = 30) 
    private Introduction introduction;

    private String resume;
    private String github;
    private String blog;
    private String youtube;
    private String skill;
    @Column(length = 100) private String academic;
    @Column(length = 100) private String career;
    @Column(length = 100) private String awards;
    @Column(length = 100) private String certificate;

    protected About() {}
}
