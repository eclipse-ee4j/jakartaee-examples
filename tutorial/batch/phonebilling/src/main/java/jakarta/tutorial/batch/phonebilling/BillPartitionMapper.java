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

import java.util.Properties;

import jakarta.batch.api.partition.PartitionMapper;
import jakarta.batch.api.partition.PartitionPlan;
import jakarta.batch.api.partition.PartitionPlanImpl;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

/* Partition mapper artifact.
 * Determines the number of partitions (2) for the bill processing step
 * and the range of bills each partition should work on.
 */
@Dependent
@Named("BillPartitionMapper")
public class BillPartitionMapper implements PartitionMapper {

    @PersistenceContext
    EntityManager em;

    @Override
    public PartitionPlan mapPartitions() throws Exception {
        /* Create a new partition plan */
        return new PartitionPlanImpl() {

            /* Auxiliary method - get the number of bills */
            public long getBillCount() {
                String query = "SELECT COUNT(b) FROM PhoneBill b";
                Query q = em.createQuery(query);
                return ((Long) q.getSingleResult()).longValue(); 
            }

            /* The number of partitions could be dynamically calculated based on
             * many parameters. In this particular example, we are setting it to
             * a fixed value for simplicity.
             */
            @Override
            public int getPartitions() {
                return 2;
            }

            @Override
            public Properties[] getPartitionProperties() {
                /* Assign an (approximately) equal number of elements
                 * to each partition. */
                long totalItems = getBillCount();
                long partItems =  totalItems / getPartitions();
                long remItems = totalItems % getPartitions();

                /* Populate a Properties array. Each Properties element
                 * in the array corresponds to a partition. */
                Properties[] props = new Properties[getPartitions()];

                for (int i = 0; i < getPartitions(); i++) {
                    props[i] = new Properties();
                    props[i].setProperty("firstItem", 
                            String.valueOf(i * partItems));
                    /* Last partition gets the remainder elements */
                    if (i == getPartitions() - 1) {
                        props[i].setProperty("numItems", 
                                String.valueOf(partItems + remItems));
                    } else {
                        props[i].setProperty("numItems", 
                                String.valueOf(partItems));
                    }
                }
                return props;
            }
        };
    }

}
