package com.envimate.examples.email_use_case.infrastructure;

import com.envimate.examples.email_use_case.domain.Body;
import com.envimate.examples.email_use_case.domain.Receiver;
import com.envimate.examples.email_use_case.domain.Sender;
import com.envimate.examples.email_use_case.domain.Subject;
import com.envimate.examples.email_use_case.services.EmailService;
import com.envimate.examples.email_use_case.usecases.email.Receipt;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
public class SimpleEmailService implements EmailService {
    @Override
    public Receipt send(final Sender sender, final Receiver receiver, final Subject subject, final Body body) {
        System.out.printf("Sending email from %s to%s with subject %s and body%s%n",
                sender,
                receiver,
                subject,
                body
        );

        return Receipt.newReceipt();
    }
}
