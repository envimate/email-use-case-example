package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.EmailAddressValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class EmailAddress {
    private final String value;

    public static EmailAddress fromString(final String value) {
        final String validated = EmailAddressValidator.ensureEmailAddress(value, "receiver");
        return new EmailAddress(validated);
    }

    public String stringValue() {
        return this.value;
    }
}
