package com.envimate.examples.email_use_case.usecases.email;

import com.envimate.examples.email_use_case.domain.DateTimeFormatted;
import com.envimate.examples.email_use_case.domain.TrackingNumber;
import com.envimate.examples.email_use_case.validation.RequiredParameterValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class Receipt {
    public final TrackingNumber trackingNumber;
    public final DateTimeFormatted date;

    public static Receipt deserialize(final TrackingNumber trackingNumber, final DateTimeFormatted dateTimeFormatted) {
        RequiredParameterValidator.ensureNotNull(trackingNumber, "trackingNumber");
        RequiredParameterValidator.ensureNotNull(dateTimeFormatted, "dateTimeFormatted");
        return new Receipt(trackingNumber, dateTimeFormatted);
    }

    public static Receipt newReceipt() {
        final TrackingNumber trackingNumber = TrackingNumber.newUnique();
        final DateTimeFormatted dateTimeFormatted = DateTimeFormatted.currentDateTimeFormatted();
        return deserialize(trackingNumber, dateTimeFormatted);
    }
}

