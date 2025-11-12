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
    @Column(length = 3) private String level;
    private String logo_url;

    protected DevEnv() {}

    public DevEnv(String name, String type, String level, String logo_url) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.logo_url = logo_url;
    }

    public static DevEnv of(String name, String type, String level, String logo_url) {
        return new DevEnv(name, type, level, logo_url);
    }

    public String getStartdate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStartdate'");
    }
}
