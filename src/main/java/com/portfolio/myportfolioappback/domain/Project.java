package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300) private String title;
    @Column(nullable = false, length = 1) private String type;
    private LocalDate startdate;
    private LocalDate enddate;
    private String git_rep_url;
    private String page_url;
    @Column(nullable = false, length = 50) private String dev_env;
    private String image_url;
    @Column(length = 50) private String company;
    @Column(length = 200) private String outline;
    @Column(length = 200) private String role;
    @Column(nullable = false, length = 2000) private String content;
    @Column(length = 200) private String result;
    @Column(length = 200) private String summary;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(name = "has_detail")
    private boolean hasDetail;
    @Column(name = "is_experience")
    private boolean isExperience;
    @Column(nullable = false) private LocalDateTime create_at;
    @Column(nullable = false) private LocalDateTime modified_at;

    protected Project() {}
}
