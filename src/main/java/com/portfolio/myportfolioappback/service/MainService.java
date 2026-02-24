package com.portfolio.myportfolioappback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.myportfolioappback.domain.DevEnv;
import com.portfolio.myportfolioappback.domain.Project;
import com.portfolio.myportfolioappback.dto.about.MainPageResponse;
import com.portfolio.myportfolioappback.dto.about.ProjectDto;
import com.portfolio.myportfolioappback.dto.about.SkillDto;
import com.portfolio.myportfolioappback.repository.DevEnvRepository;
import com.portfolio.myportfolioappback.repository.ProjectRepository;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MainService {

    private static final DateTimeFormatter YM_FMT = DateTimeFormatter.ofPattern("yyyy. M", Locale.KOREA);

    private final ProjectRepository projectRepository;
    private final DevEnvRepository devEnvRepository;
    
    public MainPageResponse getMainPage() {
        var portfolio = projectRepository.findById(1L);
        var projects = projectRepository.findAllByIdNotAndTypeIn(1L, List.of("0","1"));

        var allForLookupStream = Stream.concat(
                portfolio.stream(),
                projects.stream()
        );
        List<Long> allDevEnvIdsForLookup = allForLookupStream
                .flatMap(p -> parseCsvIds(p.getDev_env()).stream())
                .distinct()
                .toList();

        Map<Long, DevEnv> idToDevEnv = devEnvRepository.findAllById(allDevEnvIdsForLookup)
            .stream()
            .collect(Collectors.toMap(DevEnv::getId, Function.identity()));

        Function<Project, ProjectDto> toDto = p -> new ProjectDto(
            p.getId(),
            p.getTitle(),
            p.getType(),
            p.getStartdate()  != null ? p.getStartdate().format(YM_FMT) : "",
            p.getEnddate()  != null ? p.getEnddate().format(YM_FMT) : "",
            p.getGit_rep_url(),
            p.getPage_url(),
            parseCsvIds(p.getDev_env()).stream()
                    .map(idToDevEnv::get)
                    .filter(Objects::nonNull)
                    .map(e -> new SkillDto(
                            e.getId(),
                            e.getName(),
                            e.getType(),
                            e.getLevel(),
                            e.getLogo_url()
                        ))
                    .toList(),
            p.getImage_url(),
            p.getOutline(),
            p.getRole(),
            p.getContent(),
            p.getResult()
        );

        List<ProjectDto> portfolioDto =
                portfolio.map(p -> List.of(toDto.apply(p))).orElseGet(List::of);

        List<ProjectDto> personalDtos = projects.stream()
                .filter(p -> "0".equals(p.getType()))
                .map(toDto)
                .toList();

        List<ProjectDto> workDtos = projects.stream()
                .filter(p -> "1".equals(p.getType()))
                .map(toDto)
                .toList();

        return new MainPageResponse(portfolioDto, personalDtos, workDtos);
    }

    private static List<Long> parseCsvIds (String csv) {
        if (csv == null || csv.isBlank()) return List.of();
    
        return Arrays.stream(csv.split(","))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(Long::valueOf)
            .toList();
    }
}
