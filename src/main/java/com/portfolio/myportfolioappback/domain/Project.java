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
    @Column(length = 200) private String outline;
    @Column(length = 200) private String role;
    @Column(nullable = false, length = 2000) private String content;
    @Column(length = 200) private String result;
    @Column(nullable = false) private LocalDateTime create_at;
    @Column(nullable = false) private LocalDateTime modified_at;

    protected Project() {}

    public Project(String title, String type, LocalDate startdate, LocalDate enddate, String git_rep_url, String page_url, String dev_env, String image_url, String outline, String role, String content, String result, LocalDateTime create_at, LocalDateTime modified_at) {
        this.title = title;
        this.type = type;
        this.startdate = startdate;
        this.enddate = enddate;
        this.git_rep_url = git_rep_url;
        this.page_url = page_url;
        this.dev_env = dev_env;
        this.image_url = image_url;
        this.outline = outline;
        this.role = role;
        this.content = content;
        this.result = result;
        this.create_at = create_at;
        this.modified_at = modified_at;
    }

    public static Project of(String title, String type, LocalDate startdate, LocalDate enddate, String git_rep_url, String page_url, String dev_env, String image_url, String outline, String role, String content, String result, LocalDateTime create_at, LocalDateTime modified_at) {
        return new Project(title, type, startdate, enddate, git_rep_url, page_url, dev_env, image_url, outline, role, content, result, create_at, modified_at);
    }
}
