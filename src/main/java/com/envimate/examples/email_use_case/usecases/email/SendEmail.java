/*
 * Copyright (c) 2018 envimate GmbH - https://envimate.com/.
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.envimate.examples.email_use_case.usecases.email;


public class SendEmail {
//    private final EmailService emailService;

    //    @Inject if you so wish
//    public SendEmail(final EmailService emailService) {
    //      this.emailService = emailService;
    //}

    public Receipt sendEmail(final Email email) {
//             final String trackingId = emailService.send(email.sender, email.receiver, email.subject, email.body);
        System.out.printf("Sending email from %s to%s with subject %s and body%s%n",
                email.sender,
                email.receiver,
                email.subject,
                email.body
        );
        return Receipt.newReceipt();
    }
}
