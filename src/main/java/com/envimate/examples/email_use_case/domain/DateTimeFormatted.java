package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.RegexValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateTimeFormatted {
    private static final Pattern PATTERN = Pattern.compile("^\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d$");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String value;

    public static DateTimeFormatted fromStringValue(final String value) {
        final String sanitized = RegexValidator.matchingRegexValue(value, PATTERN, "dateTimeFormatted");
        return new DateTimeFormatted(sanitized);
    }

    public static DateTimeFormatted currentDateTimeFormatted() {
        final String now = DATE_TIME_FORMATTER.format(ZonedDateTime.now());
        return fromStringValue(now);
    }

    public String stringValue() {
        return this.value;
    }
}
