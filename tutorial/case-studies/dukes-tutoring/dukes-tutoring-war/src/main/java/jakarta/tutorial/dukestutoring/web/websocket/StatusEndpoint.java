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
package jakarta.tutorial.dukestutoring.web.websocket;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.ejb.EJB;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.event.Observes;
import jakarta.json.Json;
import jakarta.json.stream.JsonGenerator;
import jakarta.tutorial.dukestutoring.ejb.RequestBean;
import jakarta.tutorial.dukestutoring.events.StatusEvent;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakartaee.tutorial.dukestutoring.entity.Student;

@ServerEndpoint("/status")
@Dependent
public class StatusEndpoint {

    @EJB
    RequestBean requestBean;
    private static final Logger log = Logger.getLogger("StatusEndpoint");
    private static final Set<Session> sessions =
            Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public void open(final Session session) {
        sessions.add(session);
        /* Send list of students */
        List<Student> students = requestBean.getAllStudents();
        String studentList = jsonStudentList(students);
        try {
            session.getBasicRemote().sendText(studentList);
        } catch (IOException e) {
            log.log(Level.INFO, "[StatusEndpoint] {0}", e.getMessage());
        }
    }

    @OnClose
    public void close(final Session session) {
        sessions.remove(session);
    }

    public static synchronized void updateStatus(@Observes StatusEvent event) {
        log.info("updateStatus");
        for (Session s : sessions) {
            if (s.isOpen()) {
                try {
                    String studentUpdate = jsonStudentUpdate(event.getStudent());
                    s.getBasicRemote().sendText(studentUpdate);
                    log.log(Level.INFO, "[StatusEndpoint] {0} is now {1}", 
                            new Object[]{event.getStudent().getName(), 
                                event.getStudent().getStatus()});
                    /* Send update */
                } catch (IOException e) {
                    log.log(Level.INFO, "[StatusEndpoint] {0}", e.getMessage());
                }
            }
        }
    }
    public static synchronized void updateStatus2(Student student) {
        log.info("updateStatus2");
        for (Session s : sessions) {
            if (s.isOpen()) {
                try {
                    String studentUpdate = jsonStudentUpdate(student);
                    s.getBasicRemote().sendText(studentUpdate);
                    log.log(Level.INFO, "[StatusEndpoint] {0} is now {1}", 
                            new Object[]{student.getName(), 
                                student.getStatus()});
                    /* Send update */
                } catch (IOException e) {
                    log.log(Level.INFO, "[StatusEndpoint] {0}", e.getMessage());
                }
            }
        }
    }
    /* One list message to start
     * {
     *   "type":"list",
     *   "body": 
     *     [
     *       {"name":"StudentName1", "status":"StudentStatus1"},
     *       {"name":"StudentName2", "status":"StudentStatus2"},
     *       ...
     *     ]
     * }
     */

    private static String jsonStudentList(List<Student> students) {

        StringWriter swriter = new StringWriter();
        try (JsonGenerator gen = Json.createGenerator(swriter)) {
            gen.writeStartObject();
            gen.write("type", "list");
            gen.writeStartArray("body");
            for (Student student : students) {
                gen.writeStartObject();
                gen.write("name", student.getName());
                gen.write("status", student.getStatus().toString());
                gen.writeEnd();
            }
            gen.writeEnd();
            gen.writeEnd();
        }

        return swriter.toString();
    }

    /* One update message per student update:
     * {
     *   "type":"update",
     *   "body": {
     *     "name":"StudentName1", 
     *     "status":"StudentStatus1"
     *   }
     * }
     */
    private static String jsonStudentUpdate(Student student) {

        StringWriter swriter = new StringWriter();
        try (JsonGenerator gen = Json.createGenerator(swriter)) {
            gen.writeStartObject();
            gen.write("type", "update");
            gen.writeStartObject("body");
            gen.write("name", student.getName());
            gen.write("status", student.getStatus().toString());
            gen.writeEnd();
            gen.writeEnd();
        }

        return swriter.toString();
    }
}
