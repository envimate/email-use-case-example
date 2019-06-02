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

package com.envimate.examples.email_use_case.infrastructure.http;

import com.envimate.examples.email_use_case.usecases.ErrorDTO;
import com.envimate.examples.email_use_case.usecases.email.SendEmailUseCase;
import com.envimate.httpmate.HttpMate;
import com.envimate.httpmate.HttpMateChainKeys;
import com.envimate.httpmate.http.ContentType;
import com.envimate.httpmate.unpacking.UnsupportedContentTypeException;
import com.envimate.httpmate.usecases.UseCaseDrivenBuilder;
import com.envimate.mapmate.deserialization.Deserializer;
import com.envimate.mapmate.deserialization.validation.AggregatedValidationException;
import com.envimate.mapmate.marshalling.MarshallingType;
import com.envimate.mapmate.serialization.Serializer;
import com.google.inject.Inject;
import com.google.inject.Injector;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.envimate.examples.email_use_case.usecases.ErrorMessage.errorMessage;
import static com.envimate.httpmate.convenience.configurators.exceptions.ExceptionMappingConfigurator.toMapExceptions;
import static com.envimate.httpmate.mapmate.MapMateSerializerAndDeserializer.mapMate;
import static com.envimate.httpmate.usecases.Configurators.toCreateUseCaseInstancesUsing;


@ToString
@EqualsAndHashCode
public final class HttpMateFactory {
    private final Injector injector;
    private final Serializer serializer;
    private final Deserializer deserializer;

    @Inject
    HttpMateFactory(final Injector injector, final Serializer serializer, final Deserializer deserializer) {
        this.injector = injector;
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    public HttpMate httpMate() {
        return HttpMate.anHttpMateConfiguredAs(UseCaseDrivenBuilder.USE_CASE_DRIVEN)
                .post("/api/sendEmail", SendEmailUseCase.class)
                .mappingRequestsAndResponsesUsing(
                        mapMate()
                                .assumingTheDefaultContentType(ContentType.json())
                                .matchingTheContentType(ContentType.json())
                                .toTheMarshallerType(MarshallingType.json())
                                .usingTheSerializer(this.serializer)
                                .andTheDeserializer(this.deserializer)
                )
                .configured(toCreateUseCaseInstancesUsing(this.injector::getInstance))
                .configured(toMapExceptions()
                        .ofType(UnsupportedContentTypeException.class)
                        .toResponsesUsing((exception, metaData) -> {
                            metaData.set(HttpMateChainKeys.RESPONSE_STATUS, 415);
                        })
                        .ofType(AggregatedValidationException.class)
                        .toResponsesUsing((exception, metaData) -> {
                            metaData.set(HttpMateChainKeys.RESPONSE_STATUS, 400);
                            metaData.set(HttpMateChainKeys.RESPONSE_STRING, this.serializer.serializeToJson(ErrorDTO.error(exception)));
                        })
                        .ofAllRemainingTypesUsing((exception, metaData) -> {
                            metaData.set(HttpMateChainKeys.RESPONSE_STATUS, 500);
                            metaData.set(HttpMateChainKeys.RESPONSE_STRING, this.serializer.serializeToJson(errorMessage(exception.getMessage())));
                        })
                )
                .build();
    }
}
