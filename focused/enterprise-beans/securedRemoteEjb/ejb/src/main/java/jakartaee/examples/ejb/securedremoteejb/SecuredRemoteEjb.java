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
package jakartaee.examples.ejb.securedremoteejb;

import jakarta.ejb.Remote;

/**
 * The secured remote EJB interface.
 * 
 * @author Manfred Riem (mriem@manorrock.com)
 */
@Remote
public interface SecuredRemoteEjb {

    /**
     * Hello world.
     * 
     * @return "Hello World!"
     */
    String helloWorld();
}
