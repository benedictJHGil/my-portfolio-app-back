package com.portfolio.myportfolioappback.repository;

import com.portfolio.myportfolioappback.domain.Project;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByIdNotAndTypeIn(Long id, List<String> types);
    Optional<Project> findBySlug(String slug);
}
