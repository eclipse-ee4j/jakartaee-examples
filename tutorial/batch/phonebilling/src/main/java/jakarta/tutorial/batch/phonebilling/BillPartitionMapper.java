/*
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR(S) DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR(S) BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES WHATSOEVER
 * RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION OF CONTRACT,
 * NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN CONNECTION WITH THE
 * USE OR PERFORMANCE OF THIS SOFTWARE.
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
