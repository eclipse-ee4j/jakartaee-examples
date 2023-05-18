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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jakarta.tutorial.dukestutoring.ejb;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.tutorial.dukestutoring.events.StatusEvent;
import jakarta.tutorial.dukestutoring.util.CalendarUtil;
import jakarta.tutorial.dukestutoring.util.StatusType;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakartaee.tutorial.dukestutoring.entity.Guardian;
import jakartaee.tutorial.dukestutoring.entity.Guardian_;
import jakartaee.tutorial.dukestutoring.entity.StatusEntry;
import jakartaee.tutorial.dukestutoring.entity.StatusEntry_;
import jakartaee.tutorial.dukestutoring.entity.Student;
import jakartaee.tutorial.dukestutoring.entity.Student_;
import jakartaee.tutorial.dukestutoring.entity.TutoringSession;
import jakartaee.tutorial.dukestutoring.entity.TutoringSession_;

/**
 *
 * @author ian
 */
@Stateless
@Named
@Path("/status")
public class RequestBean {

    @PersistenceContext
    private EntityManager em;
    private static final Logger logger = Logger.getLogger("dukestutoring.ejb.RequestBean");
    private CriteriaBuilder cb;
    private TutoringSession todaysSession;
    @Resource
    SessionContext ctx;
    @Inject
    Event<StatusEvent> statusEvent;

    @PostConstruct
    private void init() {
        cb = em.getCriteriaBuilder();
    }

    public String checkIn(Student student) {
        String result;
        if (student == null) {
            logger.warning("Student is null!");
            result = "failedToCheckIn";
        } else {

            // get today's session from the convenience method
            TutoringSession tutoringSession = this.getTodaysSession();

            List<Student> students = tutoringSession.getStudents();
            if (!students.contains(student)) {
                logger.info("Adding student to session.");
                tutoringSession.getStudents().add(student);
                logger.info("Adding today's tutoring session to student.");
                List<TutoringSession> sessions = student.getSessions();
                if (sessions.isEmpty()) {
                    logger.info("Student's sessions list is empty.");
                    sessions = new ArrayList<>();
                }
                sessions.add(tutoringSession);
            }
            // set the status
            logger.log(Level.INFO, "Setting {0}''s status to IN", student.getFirstName());
            student.setStatus(StatusType.IN);
            statusEvent.fire(new StatusEvent(student));
            // create a new status entry
            logger.info("Creating a new status entry");
            StatusEntry entry = new StatusEntry(StatusType.IN, student, tutoringSession);
            // add the status entry to the tutoring session
            logger.info("Adding status entry to tutoring session");
            tutoringSession.getStatusEntries().add(entry);
            // persist the status entry
            logger.info("Persisting status entry");
            em.persist(entry);
            // modify the student
            logger.log(Level.INFO, "Merging status change to {0}", student.getFirstName());
            em.merge(student);
            // modify the tutoring session
            logger.info("Merging the status entry to tutoring session");
            em.merge(tutoringSession);

            result = "checkinSucceeded";
        }
        return result;

    }

    public String checkOut(Student student) {
        String result;

        // get today's session from the convenience method
        TutoringSession tutoringSession = this.getTodaysSession();
        logger.log(Level.INFO, "Setting {0}''s status to OUT", student.getFirstName());
        student.setStatus(StatusType.OUT);
        statusEvent.fire(new StatusEvent(student));
        logger.log(Level.INFO, "Student {0} is {1}", new Object[]{student.getFirstName(), student.getStatus()});
        StatusEntry entry = new StatusEntry(StatusType.OUT, student, tutoringSession);
        // add the status entry to the tutoring session
        tutoringSession.getStatusEntries().add(entry);
        // persist the status entry
        logger.info("Persisting status entry");
        em.persist(entry);
        // modify the student
        logger.log(Level.INFO, "Merging status change to {0}", student.getFirstName());
        em.merge(student);
        // modify the tutoring session
        logger.info("Merging the status entry to tutoring session");
        em.merge(tutoringSession);

        result = "checkoutSucceeded";
        return result;
    }

    public void checkOutAllStudents() {
        List<Student> students = this.getCheckedInStudents();
        for (Student s: students) {
            this.checkOut(s);
        }
    }

    public String atPark(Student student) {
        String result;

        // get today's session from the convenience method
        TutoringSession tutoringSession = this.getTodaysSession();
        logger.log(Level.INFO, "Setting {0}''s status to PLAYGROUND", student.getFirstName());
        student.setStatus(StatusType.PARK);
        statusEvent.fire(new StatusEvent(student));
        StatusEntry entry = new StatusEntry(StatusType.PARK, student, tutoringSession);
        // add the status entry to the tutoring session
        tutoringSession.getStatusEntries().add(entry);
        // persist the status entry
        logger.info("Persisting status entry");
        em.persist(entry);
        // modify the student
        logger.log(Level.INFO, "Merging status change to {0}", student.getFirstName());
        em.merge(student);
        // modify the tutoring session
        logger.info("Merging the status entry to tutoring session");
        em.merge(tutoringSession);

        result = "atParkSucceeded";
        return result;
    }

    public String backFromPark(Student student) {
        String result;

        // get today's session from the convenience method
        TutoringSession tutoringSession = this.getTodaysSession();
        student.setStatus(StatusType.IN);
        statusEvent.fire(new StatusEvent(student));
        StatusEntry entry = new StatusEntry(StatusType.IN, student, tutoringSession);
        // add the status entry to the tutoring session
        tutoringSession.getStatusEntries().add(entry);
        // persist the status entry
        logger.info("Persisting status entry");
        em.persist(entry);
        // modify the student
        logger.info("Merging status change to student");
        em.merge(student);
        // modify the tutoring session
        logger.info("Merging the status entry to tutoring session");
        em.merge(tutoringSession);

        result = "backFromParkSucceeded";
        return result;
    }

    public List<Student> getAllStudents() {
        CriteriaQuery<Student> cq = em.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student);
        cq.where(cb.isTrue(student.get(Student_.active)));
        cq.distinct(true);
        TypedQuery<Student> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<Student> getStudentsAtPark() {
        return this.getStudentsByStatus(StatusType.PARK);
    }

    public List<Student> getCheckedInStudents() {
        return this.getStudentsByStatus(StatusType.IN);
    }

    public List<Student> getCheckedOutStudents() {
        return this.getStudentsByStatus(StatusType.OUT);
    }

    public Student getStudentByName(String lastName, String firstName) {
        CriteriaQuery<Student> cq = em.getCriteriaBuilder().createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student);
        cq.where(cb.equal(student.get(Student_.lastName), lastName));
        cq.where(cb.equal(student.get(Student_.firstName), firstName));
        cq.distinct(true);
        TypedQuery<Student> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    public List<TutoringSession> getAllTutoringSessions() {
        CriteriaQuery<TutoringSession> cq = cb.createQuery(TutoringSession.class);
        Root<TutoringSession> session = cq.from(TutoringSession.class);
        cq.select(session);
        cq.distinct(true);
        TypedQuery<TutoringSession> q = em.createQuery(cq);
        List<TutoringSession> sessions = q.getResultList();
        logger.log(Level.INFO, "Total number of sessions: {0}", sessions.size());
        return sessions;
    }

    public void createTutoringSession() {
        try {
            todaysSession = new TutoringSession();

            // Create a formatter to pretty print the date.
            SimpleDateFormat formatter = new SimpleDateFormat("E, MMM d, yyyy");
            logger.log(Level.INFO, "Creating new tutoring session for {0}.", formatter.format(todaysSession.getSessionDate().getTime()));
            // Store it in the database
            em.persist(todaysSession);
        } catch (Exception e) {
            logger.warning("Couldn't create a new session!");
        }
    }

    // convenience method for getting the current session
    public TutoringSession getTodaysSession() {

        // get today's date and trim the time information
        Calendar today = Calendar.getInstance();
        CalendarUtil.stripTime(today);
        // find today's session

        SimpleDateFormat formatter = new SimpleDateFormat("E, MMM d, yyyy");
        logger.log(Level.INFO, "Finding tutoring session for {0}", formatter.format(today.getTime()));

        CriteriaQuery<TutoringSession> cq = cb.createQuery(TutoringSession.class);
        Root<TutoringSession> tutoringSession = cq.from(TutoringSession.class);

        cq.select(tutoringSession);
        cq.where(cb.equal(tutoringSession.get(TutoringSession_.sessionDate), today));
        cq.distinct(true);

        TypedQuery<TutoringSession> q = em.createQuery(cq);
        TutoringSession session;
        try {
            session = q.getSingleResult();
            logger.info("Found session for today.");
        } catch (NoResultException e) {
            logger.info("Today's session not found. Creating a new session.");
            session = new TutoringSession();
            em.persist(session);
        }

        return session;
    }

    public List<StatusEntry> getStatusEntriesByDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        CriteriaQuery<StatusEntry> cq = cb.createQuery(StatusEntry.class);
        Root<StatusEntry> statusEntry = cq.from(StatusEntry.class);

        cq.select(statusEntry);
        cq.where(cb.equal(statusEntry.get(StatusEntry_.statusDate), cal));
        cq.distinct(true);

        TypedQuery<StatusEntry> q = em.createQuery(cq);
        return q.getResultList();
    }

    public List<StatusEntry> getCurrentStatusEntries() {

        CriteriaQuery<StatusEntry> cq = cb.createQuery(StatusEntry.class);
        Root<StatusEntry> statusEntry = cq.from(StatusEntry.class);

        cq.select(statusEntry);
        cq.where(cb.equal(statusEntry.get(StatusEntry_.tutoringSession), this.getTodaysSession()));
        cq.distinct(true);

        TypedQuery<StatusEntry> q = em.createQuery(cq);
        return q.getResultList();

    }

    @GET
    @Path("/guardian/{guardianEmail}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed({"Guardian", "Administrator"})
    public Guardian getGuardianByEmail(@PathParam("guardianEmail") String email) {
        logger.log(Level.INFO, "Principal is: {0}", ctx.getCallerPrincipal().getName());

        CriteriaQuery<Guardian> cq = cb.createQuery(Guardian.class);
        Root<Guardian> guardian = cq.from(Guardian.class);

        cq.select(guardian);
        cq.where(cb.equal(guardian.get(Guardian_.email), email));
        cq.distinct(true);

        TypedQuery<Guardian> q = em.createQuery(cq);
        return q.getSingleResult();
    }

    @GET
    @Path("email/{guardianEmail}")
    @Produces({"application/xml", "application/json"})
    @RolesAllowed({"Guardian", "Administrator"})
    public List<Student> getStatusByGuardianEmail(@PathParam("guardianEmail") String email) {
        logger.log(Level.INFO, "Principal is: {0}", ctx.getCallerPrincipal().getName());
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        Join<Student, Guardian> guardian = student.join(Student_.guardians);

        cq.select(student);
        cq.where(cb.equal(guardian.get(Guardian_.email), email));
        cq.distinct(true);

        TypedQuery<Student> q = em.createQuery(cq);
        List<Student> results = q.getResultList();
        logger.log(Level.INFO, "Guardian {0}has {1} students.", new Object[]{email, results.size()});
        return results;
    }

    @GET
    @Path("id/{guardianId}")
    @Produces({"application/xml", "application/json"})
    public List<Student> getStatusByGuardianId(@PathParam("guardianId") Long id) {
        logger.log(Level.INFO, "Principal is: {0}", ctx.getCallerPrincipal().getName());
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        Join<Student, Guardian> guardian = student.join(Student_.guardians);

        cq.select(student);
        cq.where(cb.equal(guardian.get(Guardian_.id), id));
        cq.distinct(true);

        TypedQuery<Student> q = em.createQuery(cq);
        return q.getResultList();
    }

    // convenience method for getting students by status
    private List<Student> getStudentsByStatus(StatusType statusType) {
        CriteriaQuery<Student> cq = cb.createQuery(Student.class);
        Root<Student> student = cq.from(Student.class);
        cq.select(student);
        cq.where(cb.equal(student.get(Student_.status),
                statusType));
        cq.distinct(true);
        TypedQuery<Student> q = em.createQuery(cq);
        return q.getResultList();
    }
}
