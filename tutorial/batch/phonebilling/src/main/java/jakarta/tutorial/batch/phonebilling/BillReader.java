/*
 * Copyright (c) 2014, 2020 Oracle and/or its affiliates. All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Distribution License v. 1.0, which is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * SPDX-License-Identifier: BSD-3-Clause
 */

package jakarta.tutorial.batch.phonebilling;

import java.io.Serializable;
import java.util.Iterator;

import jakarta.batch.api.BatchProperty;
import jakarta.batch.api.chunk.ItemReader;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/* Reader batch artifact.
 * Reads bills from the entity manager.
 * This artifact is in a partitioned step.
 */
@Dependent
@Named("BillReader")
public class BillReader implements ItemReader {
    @Inject
    @BatchProperty(name = "firstItem")
    private String firstItemValue;

    @Inject
    @BatchProperty(name = "numItems")
    private String numItemsValue;

    private ItemNumberCheckpoint checkpoint;

    @PersistenceContext
    private EntityManager em;
    private Iterator iterator;

    public BillReader() {
    }

    @Override
    public void open(Serializable ckpt) throws Exception {

        /* Get the range of items to work on in this partition */
        long firstItem0 = Long.parseLong(firstItemValue);
        long numItems0 = Long.parseLong(numItemsValue);

        if (ckpt == null) {
            /* Create a checkpoint object for this partition */
            checkpoint = new ItemNumberCheckpoint();
            checkpoint.setItemNumber(firstItem0);
            checkpoint.setNumItems(numItems0);
        } else {
            checkpoint = (ItemNumberCheckpoint) ckpt;
        }

        /* Adjust range for this partition from the checkpoint */
        long firstItem = checkpoint.getItemNumber();
        long numItems = numItems0 - (firstItem - firstItem0);

        /* Obtain an iterator for the bills in this partition */
        String query = "SELECT b FROM PhoneBill b ORDER BY b.phoneNumber";
        Query q = em.createQuery(query).setFirstResult((int) firstItem)
                .setMaxResults((int) numItems);
        iterator = q.getResultList().iterator();
    }

    @Override
    public void close() throws Exception {
    }

    @Override
    public Object readItem() throws Exception {
        if (iterator.hasNext()) {
            checkpoint.nextItem();
            checkpoint.setNumItems(checkpoint.getNumItems() - 1);
            return iterator.next();
        } else {
            return null;
        }
    }

    @Override
    public Serializable checkpointInfo() throws Exception {
        return checkpoint;
    }

}
