package com.envimate.examples.email_use_case.services;

import com.envimate.examples.email_use_case.domain.Body;
import com.envimate.examples.email_use_case.domain.EmailAddress;
import com.envimate.examples.email_use_case.domain.Subject;
import com.envimate.examples.email_use_case.usecases.email.Receipt;

public interface EmailService {
    Receipt send(EmailAddress sender, EmailAddress receiver, Subject subject, Body body);
}
