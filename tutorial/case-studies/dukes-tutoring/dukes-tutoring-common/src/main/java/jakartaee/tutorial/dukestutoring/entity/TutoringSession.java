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
package jakartaee.tutorial.dukestutoring.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.tutorial.dukestutoring.util.CalendarUtil;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ian
 */
@Entity
@NamedQueries({
    @NamedQuery(name="TutoringSession.findByDate",
                query="SELECT DISTINCT t " +
                      "FROM TutoringSession t " +
                      "WHERE t.sessionDate = :date ")
})
@XmlRootElement(name = "TutoringSession")
@XmlAccessorType(XmlAccessType.FIELD)
public class TutoringSession implements Serializable {
    private static final long serialVersionUID = -7046584503641718822L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @XmlTransient
    @ManyToMany()
    private final List<Student> students;
    @OneToMany(mappedBy = "tutoringSession", cascade=CascadeType.ALL)
    private List<StatusEntry> statusEntries;
    @Temporal(value = TemporalType.DATE)
    private Calendar sessionDate;

    public TutoringSession() {
        Calendar cal = Calendar.getInstance();
        CalendarUtil.stripTime(cal);
        sessionDate = cal;
        students = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TutoringSession)) {
            return false;
        }
        TutoringSession other = (TutoringSession) object;
        if ((this.id == null && other.id != null) ||
                (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dukestutoring.entity.Session[id=" + id + "]";
    }

    /**
     * @return the students
     */
    public List<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(List<Student> students) {
        this.setStudents(students);
    }

    /**
     * @return the statusEntries
     */
    public List<StatusEntry> getStatusEntries() {
        return statusEntries;
    }

    /**
     * @param statusEntries the statusEntries to set
     */
    public void setStatusEntries(List<StatusEntry> statusEntries) {
        this.statusEntries = statusEntries;
    }

    /**
     * @return the sessionDate
     */
    public Calendar getSessionDate() {
        return sessionDate;
    }

    /**
     * @param sessionDate the sessionDate to set
     */
    public void setSessionDate(Calendar sessionDate) {
        this.sessionDate = sessionDate;
    }

}
