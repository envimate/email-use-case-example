package com.envimate.examples.email_use_case.services;

import com.envimate.examples.email_use_case.domain.Body;
import com.envimate.examples.email_use_case.domain.Receiver;
import com.envimate.examples.email_use_case.domain.Sender;
import com.envimate.examples.email_use_case.domain.Subject;
import com.envimate.examples.email_use_case.usecases.email.Receipt;

public interface EmailService {
    Receipt send(Sender sender, Receiver receiver, Subject subject, Body body);
}
