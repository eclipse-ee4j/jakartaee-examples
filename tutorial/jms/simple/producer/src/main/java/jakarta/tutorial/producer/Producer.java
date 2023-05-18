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
package jakarta.tutorial.producer;

import jakarta.annotation.Resource;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSRuntimeException;
import jakarta.jms.Queue;
import jakarta.jms.Topic;

/**
 * The Producer class consists only of a main method, which sends several
 * messages to a queue or topic.
 *
 * Run this program in conjunction with SynchConsumer or AsynchConsumer. Specify
 * "queue" or "topic" on the command line when you run the program. By default,
 * the program sends one message. Specify a number after the destination name to
 * send that number of messages.
 */
public class Producer {

    @Resource(lookup = "java:comp/DefaultJMSConnectionFactory")
    private static ConnectionFactory connectionFactory;
    @Resource(lookup = "jms/MyQueue")
    private static Queue queue;
    @Resource(lookup = "jms/MyTopic")
    private static Topic topic;

    /**
     * Main method.
     *
     * @param args the destination used by the example and, optionally, the
     * number of messages to send
     */
    public static void main(String[] args) {
        final int NUM_MSGS;

        if ((args.length < 1) || (args.length > 2)) {
            System.err.println(
                    "Program takes one or two arguments: "
                    + "<dest_type> [<number-of-messages>]");
            System.exit(1);
        }

        String destType = args[0];
        System.out.println("Destination type is " + destType);

        if (!(destType.equals("queue") || destType.equals("topic"))) {
            System.err.println("Argument must be \"queue\" or " + "\"topic\"");
            System.exit(1);
        }

        if (args.length == 2) {
            NUM_MSGS = (new Integer(args[1])).intValue();
        } else {
            NUM_MSGS = 1;
        }

        Destination dest = null;

        try {
            if (destType.equals("queue")) {
                dest = (Destination) queue;
            } else {
                dest = (Destination) topic;
            }
        } catch (JMSRuntimeException e) {
            System.err.println("Error setting destination: " + e.toString());
            System.exit(1);
        }

        /*
         * Within a try-with-resources block, create context.
         * Create producer and message.
         * Send messages, varying text slightly.
         * Send end-of-messages message.
         */
        try (JMSContext context = connectionFactory.createContext();) {
            int count = 0;

            for (int i = 0; i < NUM_MSGS; i++) {
                String message = "This is message " + (i + 1) 
                        + " from producer";
                // Comment out the following line to send many messages
                System.out.println("Sending message: " + message);
                context.createProducer().send(dest, message);
                count += 1;
            }
            System.out.println("Text messages sent: " + count);
            
            /*
             * Send a non-text control message indicating end of
             * messages.
             */
            context.createProducer().send(dest, context.createMessage());
            // Uncomment the following line if you are sending many messages
            // to two synchronous consumers
            // context.createProducer().send(dest, context.createMessage());
        } catch (JMSRuntimeException e) {
            System.err.println("Exception occurred: " + e.toString());
            System.exit(1);
        }
        System.exit(0);
    }
}
