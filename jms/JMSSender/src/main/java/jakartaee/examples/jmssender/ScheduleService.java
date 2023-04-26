package jakartaee.examples.jmssender;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Schedule;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.jms.JMSException;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonWriter;
import java.io.StringWriter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

/**
 *
 * @author Francisco Castillo (cefrancastillo@gmail.com)
 */
@Stateless
public class ScheduleService {

    private static Logger logger = Logger.getLogger(ScheduleService.class.getName());
    
    @Inject
    private Sender sender;

    @Schedule(second = "*/5", minute = "*", hour = "*")
    public void sendMessage() throws JMSException {
        try {
            JsonObject jsonObject = Json.createObjectBuilder()
                    .add("id", ThreadLocalRandom.current().nextInt(100))
                    .add("status", ThreadLocalRandom.current().nextBoolean())
                    .build();

            StringWriter stWriter = new StringWriter();

            JsonWriter jsonWriter = Json.createWriter(stWriter);
            jsonWriter.writeObject(jsonObject);
            jsonWriter.close();

            String jsonData = stWriter.toString();

            sender.sendMessage(jsonData);

            logger.info("Mensaje enviado a cola.");
        } catch (JMSException e) {
            logger.severe("Ocurrio un error al intentar enviar mensaje a cola. {}");
        }

    }
}
