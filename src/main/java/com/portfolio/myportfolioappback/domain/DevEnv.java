package com.portfolio.myportfolioappback.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class DevEnv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50) private String name;
    @Column(nullable = false, length = 100) private String type;
    @Column(nullable = false, length = 100) private String category;
    @Column(length = 3) private String level;
    private String logo_url;

    protected DevEnv() {}
}
