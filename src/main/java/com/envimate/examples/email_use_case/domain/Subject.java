package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.LengthValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Subject {
    private final String value;

    public static Subject fromStringValue(final String value) {
        final String validated = LengthValidator.ensureLength(value, 1, 256, "subject");
        return new Subject(validated);
    }

    public String stringValue() {
        return this.value;
    }
}
