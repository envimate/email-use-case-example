package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.EmailAddressValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Sender {
    private final String value;

    public static Sender fromString(final String value) {
        final String validated = EmailAddressValidator.ensureEmailAddress(value, "sender");
        return new Sender(validated);
    }

    public String internalValueForMapping() {
        return this.value;
    }
}
