package com.portfolio.myportfolioappback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.myportfolioappback.dto.*;
import com.portfolio.myportfolioappback.domain.DevEnv;
import com.portfolio.myportfolioappback.repository.*;
import com.portfolio.myportfolioappback.util.DateDuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AboutService {

    private static final DateTimeFormatter YM_FMT = DateTimeFormatter.ofPattern("yyyy. M", Locale.KOREA);

    private final AboutRepository aboutRepository;
    private final DevEnvRepository devEnvRepository;
    private final ProjectRepository projectRepository;
    private final CareerRepository careerRepository;
    private final AcademicRepository academicRepository;
    private final CertificateRepository certificateRepository;

    public AboutPageResponse getAboutPage() {
        var about = aboutRepository.findTopByOrderByIdAsc().orElseThrow(() -> new NoSuchElementException("about not found"));
        var projects = projectRepository.findByIsExperienceTrue();

        List<Long> allDevEnvIdsForLookup = projects.stream()
                .flatMap(p -> parseCsvIds(p.getDev_env()).stream())
                .distinct()
                .toList();

        Map<Long, DevEnv> idToDevEnv = devEnvRepository.findAllById(allDevEnvIdsForLookup)
            .stream()
            .collect(Collectors.toMap(DevEnv::getId, Function.identity()));

        // Skill
        var aboutSkillIds = parseCsvIds(about.getSkill());
        var aboutSkills = devEnvRepository.findAllById(aboutSkillIds);

        var aboutSkillOrderMap = new HashMap<Long, Integer>();
        for (int i = 0; i < aboutSkillIds.size(); i++) aboutSkillOrderMap.put(aboutSkillIds.get(i), i);
        aboutSkills.sort(Comparator.comparingInt(s -> aboutSkillOrderMap.getOrDefault(s.getId(), Integer.MAX_VALUE)));

        // Career
        var aboutCareerIds = parseCsvIds(about.getCareer());
        var aboutCareers = careerRepository.findAllById(aboutCareerIds);

        var aboutCareerOrderMap = new HashMap<Long, Integer>();
        for (int i = 0; i < aboutCareerIds.size(); i++) aboutCareerOrderMap.put(aboutCareerIds.get(i), i);
        aboutCareers.sort(Comparator.comparingInt(s -> aboutCareerOrderMap.getOrDefault(s.getId(), Integer.MAX_VALUE)));

        List<Long> allCareerIds = aboutCareers.stream()
            .flatMap(c -> parseCsvIds(c.getDev_env()).stream())
            .toList();

        Map<Long, String> idToName = devEnvRepository.findAllById(allCareerIds).stream()
            .collect(Collectors.toMap(DevEnv::getId, DevEnv::getName));

        Long totalDate = aboutCareers.stream()
            .map(c -> DateDuration.monthsInclusive(c.getStartdate(), c.getEnddate()))
            .mapToLong(Long::longValue)
            .sum();

        // Academic
        var aboutAcademicIds = parseCsvIds(about.getAcademic());
        var aboutAcademics = academicRepository.findAllById(aboutAcademicIds);

        var aboutAcademicOrderMap = new HashMap<Long, Integer>();
        for (int i = 0; i < aboutAcademicIds.size(); i++) aboutAcademicOrderMap.put(aboutAcademicIds.get(i), i);
        aboutAcademics.sort(Comparator.comparingInt(s -> aboutAcademicOrderMap.getOrDefault(s.getId(), Integer.MAX_VALUE)));

        // Certificate
        var aboutCertificateIds = parseCsvIds(about.getCertificate());
        var aboutCertificates = certificateRepository.findAllById(aboutCertificateIds);

        var aboutCertificateOrderMap = new HashMap<Long, Integer>();
        for (int i = 0; i < aboutCertificateIds.size(); i++) aboutCertificateOrderMap.put(aboutCertificateIds.get(i), i);
        aboutCertificates.sort(Comparator.comparingInt(s -> aboutCertificateOrderMap.getOrDefault(s.getId(), Integer.MAX_VALUE)));

        var aboutDto = new AboutDto(
            about.getId(), 
            about.getName_kr(), 
            about.getName_en(),
            about.getNickname(),
            about.getBirthdate() != null ? about.getBirthdate().format(YM_FMT) : null,
            about.getPhoneNumber(),
            about.getEmail(),
            about.getJob(),
            about.getIntroduction(),
            about.getResume(),
            about.getGithub(),
            about.getBlog(),
            about.getYoutube()
        );

        var skillDto = aboutSkills.stream()
            .map(s -> new SkillDto(s.getId(), s.getName(), s.getType(), s.getLevel(), s.getCategory(), s.getLogo_url()))
            .toList();
        
        var experienceDto = projects.stream()
            .map(p -> new ExperienceDto(
                p.getId(),
                p.getTitle(),
                p.getType(),
                Optional.ofNullable(p.getStartdate())
                    .map(start -> formatDateRangeWithDuration(start, p.getEnddate(), p.getType()))
                    .orElse(null),
                p.getRole(),
                p.getSummary(),
                p.getCompany(),
                parseCsvIds(p.getDev_env()).stream().map(idToName::get).filter(Objects::nonNull).toList()))
            .toList();

        var careerDto = aboutCareers.stream()
            .map(c -> new CareerDto(
                    c.getId(),
                    c.getName(),
                    c.getNameEn(),
                    c.getStartdate().format(YM_FMT),
                    c.getEnddate() != null ? c.getEnddate().format(YM_FMT) : "재직중",
                    DateDuration.durationLabel(c.getStartdate(), c.getEnddate()),
                    c.getReason(),
                    c.getDepartment(),
                    c.getRank(),
                    c.getWork(),
                    c.getPay(),
                    c.getLocation(),
                    c.getTask(),
                    parseCsvIds(c.getDev_env()).stream().map(idToName::get).filter(Objects::nonNull).toList() ,
                    c.getContent()))
            .toList();

        var academicDto = aboutAcademics.stream()
            .map(a -> new AcademicDto(
                    a.getId(),
                    a.getName(),
                    a.getStartdate().format(YM_FMT),
                    a.getEnddate() != null ? a.getEnddate().format(YM_FMT) : "재학중",
                    a.getMajor(),
                    a.getGrade(),
                    a.isFinal()))
            .toList();

        var certificateDto = aboutCertificates.stream()
            .map(c -> new CertificateDto(
                    c.getId(),
                    c.getName(),
                    c.getOrganization(),
                    c.getDate().format(YM_FMT),
                    c.getLevel(),
                    c.getScore(),
                    c.getEvaluate().equals("2") ? "최종합격" : c.getEvaluate().equals("1") ? "실기합격" : "필기합격"))
            .toList();

        return new AboutPageResponse(aboutDto, skillDto, experienceDto, DateDuration.formatYearsMonths(totalDate), careerDto, academicDto, certificateDto);
    }
    
    private static List<Long> parseCsvIds (String csv) {
        if (csv == null || csv.isBlank()) return List.of();
    
        return Arrays.stream(csv.split(","))
            .map(String::trim)
            .filter(s -> !s.isEmpty())
            .map(Long::valueOf)
            .toList();
    }

    private String formatDateRangeWithDuration(LocalDate start, LocalDate end, String type) {
        if ("0".equals(type)) {
            String year = start.format(DateTimeFormatter.ofPattern("yyyy"));
            String status = (end != null) ? "(개인 프로젝트)" : "~ 현재 (운영 중)";
            return String.format("%s %s", year, status);
        }

        if ("1".equals(type)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM");
            String startStr = start.format(formatter);
            String endStr = (end != null) ? end.format(formatter) : "현재";
            String duration = DateDuration.durationLabel(start, end);
            
            return String.format("%s ~ %s (%s)", startStr, endStr, duration);
        }

        return "";
    }
}

