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
package jakarta.tutorial.clientsessionmdb.sb;

import jakarta.ejb.Remote;

/**
 * Remote interface for Publisher enterprise bean. Declares one
 * business method.
 */
@Remote
public interface PublisherRemote {
    public void publishNews();
}
