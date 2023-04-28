/*
 * Copyright (c) 2017, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.custom_identity_store;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.security.enterprise.authentication.mechanism.http.BasicAuthenticationMechanismDefinition;

@BasicAuthenticationMechanismDefinition(
        realmName = "file"
)

@ApplicationScoped
@Named
public class ApplicationConfig {

}
