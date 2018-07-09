/*
 * Copyright (c) 2014 Red Hat, Inc. and/or its affiliates.
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package _private;

import javax.batch.operations.BatchRuntimeException;

import org.jboss.logging.Messages;
import org.jboss.logging.annotations.Message;
import org.jboss.logging.annotations.MessageBundle;
import org.jboss.logging.annotations.ValidIdRange;

@MessageBundle(projectCode = "JBERET")
@ValidIdRange(min = 73000, max = 73499)
public interface JBeretCamelMessages {
    JBeretCamelMessages MESSAGES = Messages.getBundle(JBeretCamelMessages.class);

    @Message(id = 73000, value = "CamelContext not available in %s")
    BatchRuntimeException noCamelContext(Object requestingObject);

    @Message(id = 73001, value = "Invalid batch property value: '%s' = '%s'")
    BatchRuntimeException invalidPropertyValue(String name, String value);

    @Message(id = 73002, value = "Invalid JBeret component URI: '%s'")
    BatchRuntimeException invalidJBeretComponentUri(String remainingPath);

    @Message(id = 73003, value = "Invalid or missing parameter in JBeret component URI, key: '%s', value: '%s'")
    BatchRuntimeException invalidOrMissingParameterInJBeretComponentUrk(String key, String value);

}
