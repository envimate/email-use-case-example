package com.envimate.examples.email_use_case.usecases;

import com.envimate.examples.email_use_case.validation.LengthValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class ErrorMessage {
    private final String value;

    public static ErrorMessage fromStringValue(final String value) {
        final String validated = LengthValidator.ensureMinLength(value, 1, "errorMessage");
        return new ErrorMessage(validated);
    }

    public String stringValue() {
        return this.value;
    }
}
