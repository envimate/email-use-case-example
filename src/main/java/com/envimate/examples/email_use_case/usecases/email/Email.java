package com.envimate.examples.email_use_case.usecases.email;

import com.envimate.examples.email_use_case.domain.Body;
import com.envimate.examples.email_use_case.domain.EmailAddress;
import com.envimate.examples.email_use_case.domain.Subject;
import com.envimate.examples.email_use_case.validation.RequiredParameterValidator;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public final class Email {
    public final EmailAddress sender;
    public final EmailAddress receiver;
    public final Subject subject;
    public final Body body;

    public static Email deserialize(final EmailAddress sender,
                                final EmailAddress receiver,
                                final Subject subject,
                                final Body body) {
        RequiredParameterValidator.ensureNotNull(sender, "sender");
        RequiredParameterValidator.ensureNotNull(receiver, "receiver");
        RequiredParameterValidator.ensureNotNull(body, "body");
        return new Email(sender, receiver, subject, body);
    }
}
