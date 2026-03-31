package com.portfolio.myportfolioappback.domain;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.portfolio.myportfolioappback.domain.json.*;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class ProjectDetail {

    @Id
    private Long id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Project project;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Overview overview;

    @Column(name = "architecture_image_url", length = 500)
    private String architectureImageUrl;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "architecture_description", columnDefinition = "json")
    private ArchitectureDescription architectureDescription;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "core_features", columnDefinition = "json")
    private List<CoreFeature> coreFeatures;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "tech_decisions", columnDefinition = "json")
    private List<TechDecisions> techDecisions;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Troubleshooting> troubleshooting;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "performance_improvements", columnDefinition = "json")
    private List<PerformanceImprovement> performanceImprovements;

    @Column(name = "operations_experience", columnDefinition = "text")
    private String operationsExperience;

    @Column(columnDefinition = "text")
    private String retrospective;

    @Column(nullable = false, name = "created_at") 
    private LocalDateTime createdAt;

    @Column(nullable = false, name = "modified_at") 
    private LocalDateTime modifiedAt;

    protected ProjectDetail() {}
}
