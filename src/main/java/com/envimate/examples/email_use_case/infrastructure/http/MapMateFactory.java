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

import com.envimate.examples.email_use_case.domain.Sender;
import com.envimate.examples.email_use_case.usecases.ErrorDTO;
import com.envimate.examples.email_use_case.usecases.ErrorMessage;
import com.envimate.examples.email_use_case.usecases.email.SendEmailUseCase;
import com.envimate.examples.email_use_case.validation.CustomTypeValidationException;
import com.envimate.mapmate.deserialization.Deserializer;
import com.envimate.mapmate.deserialization.validation.ExceptionMappingWithPropertyPath;
import com.envimate.mapmate.deserialization.validation.ValidationError;
import com.envimate.mapmate.filters.ClassFilters;
import com.envimate.mapmate.serialization.Serializer;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static com.envimate.mapmate.filters.ClassFilters.allClassesThatHaveAPublicStringMethodWithZeroArgumentsNamed;

@ToString
@EqualsAndHashCode
public final class MapMateFactory {
    private MapMateFactory() {
    }

    public static Serializer serializer() {
        return Serializer.aSerializer()
                .thatScansThePackage(Sender.class.getPackageName())
                .forCustomPrimitives()
                .filteredBy(allClassesThatHaveAPublicStringMethodWithZeroArgumentsNamed("internalValueForMapping"))
                .thatAre()
                .serializedUsingTheMethodNamed("internalValueForMapping")
                .thatScansThePackage(ErrorMessage.class.getPackageName())
                .forCustomPrimitives()
                .filteredBy(allClassesThatHaveAPublicStringMethodWithZeroArgumentsNamed("internalValueForMapping"))
                .thatAre()
                .serializedUsingTheMethodNamed("internalValueForMapping")
                .thatScansThePackage(SendEmailUseCase.class.getPackageName())
                .forDataTransferObjects()
                .filteredBy(ClassFilters.havingFactoryMethodNamed("restore"))
                .thatAre()
                .serializedByItsPublicFields()
                .thatScansThePackage(ErrorDTO.class.getPackageName())
                .forDataTransferObjects()
                .filteredBy(ClassFilters.havingFactoryMethodNamed("restore"))
                .thatAre()
                .serializedByItsPublicFields()
                .withJsonMarshaller(new Gson()::toJson)
                .build();
    }

    public static Deserializer deserializer() {
        return Deserializer.aDeserializer()
                .thatScansThePackage(Sender.class.getPackageName())
                .forCustomPrimitives()
                .filteredBy(allClassesThatHaveAPublicStringMethodWithZeroArgumentsNamed("internalValueForMapping"))
                .thatAre()
                .deserializedUsingTheStaticMethodWithSingleStringArgument()
                .thatScansThePackage(SendEmailUseCase.class.getPackageName())
                .forDataTransferObjects()
                .filteredBy(ClassFilters.havingFactoryMethodNamed("restore"))
                .thatAre()
                .deserializedUsingTheFactoryMethodNamed("restore")
                .withJsonUnmarshaller(new Gson()::fromJson)
                .mappingExceptionUsing(CustomTypeValidationException.class, new ExceptionMappingWithPropertyPath() {
                    @Override
                    public ValidationError map(final Throwable t, final String propertyPath) {
                        return new ValidationError(t.getMessage(), propertyPath);
                    }
                })
                .build();
    }
}
