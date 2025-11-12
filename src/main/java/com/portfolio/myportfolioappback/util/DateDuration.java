package com.portfolio.myportfolioappback.util;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public final class DateDuration {

    private DateDuration() {}

    public static long monthsInclusive(LocalDate start, LocalDate end) {
        Objects.requireNonNull(start, "start is required");
        LocalDate effectiveEnd = (end != null) ? end : LocalDate.now();
        long months = ChronoUnit.MONTHS.between(start.withDayOfMonth(1), effectiveEnd.withDayOfMonth(1)) + 1;
        return Math.max(months, 0);
    }

    public static String formatYearsMonths(Long months) {
        long years = months / 12;
        long rem = months % 12;
        if (years > 0 && rem > 0) return years + "년 " + rem + "개월";
        if (years > 0) return years + "년";
        return rem + "개월";
    }

    public static String durationLabel(LocalDate start, LocalDate end) {
        return formatYearsMonths(monthsInclusive(start, end));
    }
}
