/*
 * Copyright (c) 2014, 2019 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.phonebilling;

import java.io.Serializable;

/* Checkpoint class for the chunk batch artifacts */
public class ItemNumberCheckpoint implements Serializable {
    
    private static final long serialVersionUID = 5999782131990251192L;
    private long itemNumber;
    private long numItems;
    
    public ItemNumberCheckpoint() {
        itemNumber = 0;
    }
    
    public ItemNumberCheckpoint(int numItems) {
        this();
        this.numItems = numItems;
    }
    
    public long getItemNumber() {
        return itemNumber;
    }
    
    public void setNumItems(long numItems) {
        this.numItems = numItems;
    }
    
    public long getNumItems() {
        return numItems;
    }
    
    public void nextItem() {
        itemNumber++;
    }
    
    public void setItemNumber(long item) {
        itemNumber = item;
    }
}
