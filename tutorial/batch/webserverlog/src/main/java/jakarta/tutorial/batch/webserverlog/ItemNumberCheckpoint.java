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
package jakarta.tutorial.batch.webserverlog;

import java.io.Serializable;

/* Class for checkpoint objects.
 */
public class ItemNumberCheckpoint implements Serializable {
    
    private static final long serialVersionUID = -7455017703127938364L;
    private long lineNum;

    public ItemNumberCheckpoint() {
        lineNum = 0;
    }

    public long getLineNum() {
        return lineNum;
    }

    public void nextLine() {
        lineNum++;
    }
}
