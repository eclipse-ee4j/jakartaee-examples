/*
 * Copyright (c), Eclipse Foundation, Inc. and its licensors.
 *
 * All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v1.0, which is available at
 * https://www.eclipse.org/org/documents/edl-v10.php
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */
package jakartaee.examples.eesecurity.inmemorystore;

import static java.util.Arrays.asList;
import java.util.HashSet;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;
import jakarta.security.enterprise.identitystore.IdentityStore;

/**
 * The in-memory identity store.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@ApplicationScoped
public class InMemoryIdentityStore implements IdentityStore {

    /**
     * Validate the username / password credential.
     * 
     * @param usernamePasswordCredential the username / password credential.
     * @return the result.
     */
    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {
        if (usernamePasswordCredential.compareTo("test", "test")) {
            return new CredentialValidationResult("test", new HashSet<>(asList("user")));
        }
        return INVALID_RESULT;
    }
}
