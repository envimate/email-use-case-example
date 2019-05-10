package com.envimate.examples.email_use_case.validation;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import static com.envimate.examples.email_use_case.validation.CustomTypeValidationException.customTypeValidationException;

public final class EmailAddressValidator {
    private EmailAddressValidator() {
    }

    public static String ensureEmailAddress(final String value, final String description) {
        final String sanitized = SanityValidator.sanitized(value, description);
        try {
            final InternetAddress emailAddress = new InternetAddress(sanitized);
            emailAddress.validate();
            return sanitized.toLowerCase();
        } catch (final AddressException ignored) {
            throw customTypeValidationException("Invalid email address: '%s'", value);
        }
    }
}
