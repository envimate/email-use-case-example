package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.LengthValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Body {
    private final String value;

    public static Body fromString(final String value) {
        final String emailAddress = LengthValidator.ensureLength(value, 1, 1000, "body");
        return new Body(emailAddress);
    }

    public String internalValueForMapping() {
        return this.value;
    }
}
