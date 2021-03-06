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

package com.envimate.examples.email_use_case.usecases;

import com.envimate.examples.email_use_case.validation.RequiredParameterValidator;
import com.envimate.mapmate.deserialization.validation.AggregatedValidationException;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.stream.Collectors;

@ToString
@EqualsAndHashCode
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorDTO {
    public final ErrorMessage message;

    public static ErrorDTO deserialize(final ErrorMessage message) {
        RequiredParameterValidator.ensureNotNull(message, "message of the Error");
        return new ErrorDTO(message);
    }

    public static ErrorDTO error(final AggregatedValidationException aggregatedValidationException) {
        final String message = aggregatedValidationException.getValidationErrors()
                .stream()
                .map(validationError -> {
                            if (!validationError.propertyPath.isEmpty()) {
                                return validationError.propertyPath + ": " + validationError.message;
                            } else {
                                return validationError.message;
                            }
                        }
                ).collect(Collectors.joining(","));

        final ErrorMessage errorMessage = ErrorMessage.fromStringValue(message);

        return deserialize(errorMessage);
    }
}
