/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jakartaee.examples.mdb;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

/**
 *
 * @author Francisco Castillo (cefrancastillo@gmail.com)
 */
public class ReceiverMDB implements MessageListener {

  @Override
  public void onMessage(Message msg) {
    try {

      // Obtenemos el mensaje
      TextMessage textMessage = (TextMessage) msg;

      System.out.println("Mensaje recibido" + textMessage.getText());
    } catch (JMSException ex) {
      throw new RuntimeException("Ocurrio un error al procesar el mensaje. "
              + ex.getLocalizedMessage());
    }
  }

}
