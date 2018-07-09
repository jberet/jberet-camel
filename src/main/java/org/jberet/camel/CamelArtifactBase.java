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

import javax.batch.api.BatchProperty;
import javax.inject.Inject;

import _private.JBeretCamelMessages;
import org.apache.camel.CamelContext;
import org.apache.camel.Endpoint;

/**
 * Abstract base class for all Camel-related batch artifacts.
 *
 * @since 1.3.0
 */
public  abstract class CamelArtifactBase {
    /**
     * URI of the Camel endpoint for this artifact type.
     * This is a required batch property.
     */
    @Inject
    @BatchProperty(name = "endpoint")
    protected String endpointUri;

    /**
     * Camel endpoint for this artifact type.
     */
    protected Endpoint endpoint;

    /**
     * Injected {@code CamelContext}.
     */
    @Inject
    protected CamelContext camelContext;

    /**
     * Performs certain validations and initialization.
     */
    protected void init() {
        if (camelContext == null) {
            throw JBeretCamelMessages.MESSAGES.noCamelContext(this);
        }
        if (endpointUri == null || endpointUri.isEmpty()) {
            throw JBeretCamelMessages.MESSAGES.invalidPropertyValue("endpoint", endpointUri);
        }
        endpoint = camelContext.getEndpoint(endpointUri);
    }
}
