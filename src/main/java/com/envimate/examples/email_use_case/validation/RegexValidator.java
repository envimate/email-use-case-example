package com.envimate.examples.email_use_case.validation;

import java.util.function.BiFunction;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.envimate.examples.email_use_case.validation.CustomTypeValidationException.customTypeValidationException;

public final class RegexValidator {
    private RegexValidator() {
    }

    public static String matchingRegexValue(final String value,
                                            final Pattern pattern,
                                            final String description) {
        return matchingRegexValue(value, pattern, description,
                (v, p) -> String.format("%s does not match regex %s", value, pattern)
        );
    }

    public static String notMatchingRegexValue(final String value,
                                               final Pattern pattern,
                                               final String description) {
        final String sanitized = SanityValidator.sanitized(value, description);
        final Matcher matcher = pattern.matcher(sanitized);
        if (!matcher.matches()) {
            return sanitized;
        } else {
            throw customTypeValidationException("%s must not match regex %s", value, pattern);
        }
    }

    public static String matchingRegexValue(final String value,
                                            final Pattern pattern,
                                            final String description,
                                            final BiFunction<String, Pattern, String> errorMessageGenerator) {
        final String sanitized = SanityValidator.sanitized(value, description);
        final Matcher matcher = pattern.matcher(sanitized);
        if (matcher.matches()) {
            return sanitized;
        } else {
            throw customTypeValidationException(errorMessageGenerator.apply(value, pattern));
        }
    }
}
