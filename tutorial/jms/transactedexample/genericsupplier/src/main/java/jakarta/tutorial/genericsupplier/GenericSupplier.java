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
package jakarta.tutorial.genericsupplier;

import java.util.Random;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.Queue;
import jakarta.jms.Topic;

/**
 * The GenericSupplier class receives an item order from the vendor and sends a
 * message accepting or refusing it.
 */
public class GenericSupplier {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/OTopic")
    private static Topic supplierOrderTopic;
    static String PRODUCT_NAME;
    static boolean ready = false;
    static int quantity = 0;

    /**
     * Constructor. Instantiates the supplier as the supplier for the kind of
     * item to be ordered.
     *
     * @param itemName the name of the item being ordered
     */
    public GenericSupplier(String itemName) {
        PRODUCT_NAME = itemName;
    }

    /**
     * Timer method. Completes when ready is set to true, after context is
     * started. Sleep prevents supplier from getting ahead of itself on fast
     * machines.
     */
    void waitForTopicConsumer() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }

        while (!(ready)) {
        }
    }

    /**
     * Checks to see if there are enough items in inventory. Rather than go to a
     * database, it generates a random number related to the order quantity, so
     * that some of the time there won't be enough in stock.
     *
     * @return the number of items in inventory
     */
    public static int checkInventory() {
        Random rgen = new Random();

        return (rgen.nextInt(quantity * 5));
    }

    public static void main(String[] args) {
        {
            JMSConsumer receiver;
            Message inMessage;
            MapMessage orderMessage;
            MapMessage outMessage;

            if (args.length != 1) {
                System.out.println("Program takes string argument, either CPU or HD");
                System.exit(1);
            }
            PRODUCT_NAME = args[0];
            if ("HD".equals(PRODUCT_NAME)) {
                PRODUCT_NAME = "Hard Drive";
            }
            System.out.println("Starting " + PRODUCT_NAME + " supplier");

            /*
             * Create context, then create receiver for order topic, which 
             * starts message delivery.
             */
            try (JMSContext context = connectionFactory.createContext(
                    JMSContext.SESSION_TRANSACTED);) {
                receiver = context.createConsumer(supplierOrderTopic);

                // Context has started, set ready to true
                ready = true;

                /*
                 * Keep checking supplier order topic for order
                 * request until end-of-message-stream message is
                 * received. Receive order and send an order
                 * confirmation as one transaction.
                 */
                while (true) {
                    try {
                        inMessage = receiver.receive();

                        if (inMessage instanceof MapMessage) {
                            orderMessage = (MapMessage) inMessage;
                        } else {
                            /*
                             * Message is an end-of-message-stream
                             * message. Send a similar message to
                             * reply queue, commit transaction, then
                             * stop processing orders by breaking out
                             * of loop.
                             */
                            context.createProducer().send(inMessage.getJMSReplyTo(),
                                    context.createMessage());
                            context.commit();

                            break;
                        }

                        /*
                         * Extract quantity ordered from order
                         * message.
                         */
                        quantity = orderMessage.getInt("Quantity");
                        System.out.println(
                                PRODUCT_NAME + " Supplier: Vendor ordered "
                                + quantity + " " + PRODUCT_NAME + "(s)");

                        /*
                         * Create sender and message for reply queue.
                         * Set order number and item; check inventory
                         * and set quantity available.
                         * Send message to vendor and commit
                         * transaction.
                         */
                        outMessage = context.createMapMessage();
                        outMessage.setInt(
                                "VendorOrderNumber",
                                orderMessage.getInt("VendorOrderNumber"));
                        outMessage.setString("Item", PRODUCT_NAME);

                        int numAvailable = checkInventory();

                        if (numAvailable >= quantity) {
                            outMessage.setInt("Quantity", quantity);
                        } else {
                            outMessage.setInt("Quantity", numAvailable);
                        }

                        context.createProducer().send(
                                (Queue) orderMessage.getJMSReplyTo(),
                                outMessage);
                        System.out.println(
                                PRODUCT_NAME + " Supplier: Sent "
                                + outMessage.getInt("Quantity") + " "
                                + outMessage.getString("Item") + "(s)");
                        context.commit();
                        System.out.println(
                                "  " + PRODUCT_NAME
                                + " Supplier: Committed transaction");
                    } catch (Exception e) {
                        System.err.println(
                                PRODUCT_NAME + " Supplier: Exception occurred: "
                                + e.toString());
                    }
                }
            } catch (JMSRuntimeException ee) {
                System.err.println(
                        PRODUCT_NAME + " Supplier: Exception occurred: "
                        + ee.toString());
            }
        }
    }
}
