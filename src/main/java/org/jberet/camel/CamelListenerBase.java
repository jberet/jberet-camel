/*
 * Copyright (c) 2016 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.jberet.camel;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.batch.operations.BatchRuntimeException;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

import _private.JBeretCamelLogger;
import org.apache.camel.ProducerTemplate;

/**
 * Base class for JBeret Camel listener classes that sends execution events
 * to the configured Camel endpoint.
 *
 * @since 1.3.0
 */
public abstract class CamelListenerBase extends CamelArtifactBase {
    /**
     * Injection of {@code javax.batch.runtime.context.JobContext} by batch
     * runtime.
     */
    @Inject
    protected JobContext jobContext;

    /**
     * Batch job operator.
     */
    protected JobOperator jobOperator;

    /**
     * Camel producer template used to send job execution events.
     */
    protected ProducerTemplate producerTemplate;

    @PostConstruct
    private void postConstruct() {
        init();
        if (producerTemplate == null) {
            producerTemplate = camelContext.createProducerTemplate();
        }
        try {
            producerTemplate.start();
        } catch (Exception e) {
            throw new BatchRuntimeException(e);
        }
        jobOperator = BatchRuntime.getJobOperator();
    }

    @PreDestroy
    private void preDestroy() {
        if (producerTemplate != null) {
            try {
                producerTemplate.stop();
            } catch (Exception e) {
                JBeretCamelLogger.LOGGER.failToStop(e, this);
            }
        }
    }
}
