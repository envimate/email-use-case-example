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

    public static EmailAddress fromStringValue(final String value) {
        final String validated = EmailAddressValidator.ensureEmailAddress(value, "emailAddress");
        return new EmailAddress(validated);
    }

    public String stringValue() {
        return this.value;
    }
}
