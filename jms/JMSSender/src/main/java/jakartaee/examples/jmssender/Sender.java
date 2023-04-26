/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jakartaee.examples.jmssender;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageProducer;
import jakarta.jms.Queue;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

/**
 *
 * @author Francisco Castillo (cefrancastillo@gmail.com)
 */
@Stateless
public class Sender {

    @Resource(mappedName = "jms/DemoConnectionFactory")
    private ConnectionFactory connectionFactory;

    @Resource(mappedName = "jms/Demo")
    private Queue queue;

    public void sendMessage(String text) throws JMSException {

        Connection conn = null;
        Session session = null;
        try {
            conn = connectionFactory.createConnection();
            conn.start();

            session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Productor vinculado al destino
            MessageProducer messageProducer = session.createProducer(queue);

            // Creamos un mensaje y recibo parametro
            TextMessage txtMessage = session.createTextMessage(text);
            //TextMessage txtMessage = session.createTextMessage(((TextMessage) message).getText());

            // Enviamos mensaje
            messageProducer.send(txtMessage);
        } finally {
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
}
