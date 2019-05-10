package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.EmailAddressValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Receiver {
    private final String value;

    public static Receiver fromString(final String value) {
        final String validated = EmailAddressValidator.ensureEmailAddress(value, "receiver");
        return new Receiver(validated);
    }

    public String internalValueForMapping() {
        return this.value;
    }
}
