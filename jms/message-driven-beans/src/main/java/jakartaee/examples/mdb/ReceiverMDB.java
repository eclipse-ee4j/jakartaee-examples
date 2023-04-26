package jakartaee.examples.mdb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Castillo (cefrancastillo@gmail.com)
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
}, mappedName = "jms/Demo")
public class ReceiverMDB implements MessageListener {

    private static Logger logger = Logger.getLogger(ReceiverMDB.class.getName());

    @Override
    public void onMessage(Message msg) {
        try {
            // Obtenemos el mensaje
            TextMessage textMessage = (TextMessage) msg;

            // Mostramos su contenido
            logger.info("Mensaje recibido: " + textMessage.getText());
        } catch (JMSException ex) {
            logger.severe("Ocurrio un error al intentar procesar mensaje: " + ex.getLocalizedMessage());
        }
    }

}
