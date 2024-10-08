/*
 * Copyright 2022 LINE Corporation
 *
 * LINE Corporation licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.linecorp.armeria.resilience4j.circuitbreaker;

import java.util.concurrent.TimeUnit;

import com.linecorp.armeria.common.annotation.UnstableApi;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;

/**
 * An exception that is returned when a {@link CircuitBreaker#onError(long, TimeUnit, Throwable)}
 * is invoked without a distinctive exception.
 */
@UnstableApi
public final class FailedCircuitBreakerDecisionException extends RuntimeException {

    private static final long serialVersionUID = 9215915943941088196L;

    private static final FailedCircuitBreakerDecisionException DEFAULT =
            new FailedCircuitBreakerDecisionException();

    /**
     * Returns the default {@link FailedCircuitBreakerDecisionException}.
     */
    public static FailedCircuitBreakerDecisionException of() {
        return DEFAULT;
    }

    private FailedCircuitBreakerDecisionException() {
        super(null, null, false, false);
    }
}
