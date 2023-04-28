/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.traffic.rar.api;

/* Thanks to the functionality introduced in JCA 1.7,
 * this interface - and the connector - do not have to change when the MDB implements
 * a new method. The MDB just uses an annotation for command methods. */
public interface TrafficListener {
    
}
