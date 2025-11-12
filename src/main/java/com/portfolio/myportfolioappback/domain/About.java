package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

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
    private String github;
    private String blog;
    private String youtube;
    private String skill;
    @Column(length = 100) private String academic;
    @Column(length = 100) private String career;
    @Column(length = 100) private String awards;
    @Column(length = 100) private String certificate;

    protected About() {}

    public About(String name_kr, String name_en, String nickname, LocalDate birthdate, String phoneNumber, String email, String github, String blog, String youtube, String skill, String academic, String career, String awards, String certificate) {
        this.name_kr = name_kr;
        this.name_en = name_en;
        this.nickname = nickname;
        this.birthdate = birthdate;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.github = github;
        this.blog = blog;
        this.youtube = youtube;
        this.skill = skill;
        this.academic = academic;
        this.career = career;
        this.awards = awards;
        this.certificate = certificate;
    }

    public static About of(String name_kr, String name_en, String nickname, LocalDate birthdate, String phoneNumber, String email, String github, String blog, String youtube, String skill, String academic, String career, String awards, String certificate) {
        return new About(name_kr, name_en, nickname, birthdate, phoneNumber, email, github, blog, youtube, skill, academic, career, awards, certificate);
    }
}
