package com.envimate.examples.email_use_case.domain;

import com.envimate.examples.email_use_case.validation.UuidValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class TrackingNumber {
    private final UUID value;

    public static TrackingNumber fromString(final String value) {
        final UUID validated = UuidValidator.ensureUuid(value, "trackingNumber");
        return new TrackingNumber(validated);
    }

    public static TrackingNumber newUnique() {
        return fromString(UUID.randomUUID().toString());
    }

    public String stringValue() {
        return this.value.toString();
    }
}
