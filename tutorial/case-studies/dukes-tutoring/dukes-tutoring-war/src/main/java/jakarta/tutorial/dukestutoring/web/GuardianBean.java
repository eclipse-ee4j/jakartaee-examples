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
package jakarta.tutorial.dukestutoring.web;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.tutorial.dukestutoring.ejb.AdminBean;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakartaee.tutorial.dukestutoring.entity.Guardian;
import jakartaee.tutorial.dukestutoring.entity.Student;

@Named
@RequestScoped
public class GuardianBean {

    private static final Logger logger = 
            Logger.getLogger("dukestutoring.web.GuardianBean");
    @Inject
    private AdminBean adminBean;
    private Guardian guardian;
    @NotNull
    private String firstName;
    private String middleName;
    @NotNull
    private String lastName;
    private String nickname;
    private String suffix;
    protected String email;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}",
            message = "{invalid.phonenumber}")
    protected String homePhone;
    @Pattern(regexp = "\\(\\d{3}\\) \\d{3}-\\d{4}",
            message = "{invalid.phonenumber}")
    protected String mobilePhone;
    protected Guardian selectedGuardian;
    protected List<Guardian> selectedGuardians;
    protected List<Guardian> allGuardians;

    /**
     * Creates a new instance of GuardianBean
     */
    public GuardianBean() {
    }

    public Guardian getGuardian() {
        return guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Guardian> getAllGuardians() {
        if (allGuardians == null) {
            try {
                this.allGuardians = adminBean.getAllGuardians();
            } catch (Exception ex) {
                logger.log(Level.SEVERE, 
                        "adminBean.getAllGuardians returned an error{0}", 
                        ex.getMessage());
            }
        }
        return this.allGuardians;

    }

    /**
     * Get the list of guardians except those already assigned to this student
     *
     * @param student the student we want a list of possible new guardians for
     * @return the list of possible guardians
     */
    public List<Guardian> getAllOtherGuardians(Student student) {
        List<Guardian> otherGuardians = getAllGuardians();
        for (Guardian guardian : student.getGuardians()) {
            otherGuardians.remove(guardian);
        }
        return otherGuardians;
    }

    public void setAllGuardians(List<Guardian> allGuardians) {
        this.allGuardians = allGuardians;
    }

    public List<Guardian> getSelectedGuardians() {
        return selectedGuardians;
    }

    public void setSelectedGuardians(List<Guardian> selectedGuardians) {
        this.selectedGuardians = selectedGuardians;
    }

    public Guardian getSelectedGuardian() {
        return selectedGuardian;
    }

    public void setSelectedGuardian(Guardian selectedGuardian) {
        this.selectedGuardian = selectedGuardian;
    }

    public String submit(Student student) {

        this.guardian = adminBean.createGuardian(firstName, middleName,
                lastName, nickname, suffix, email, homePhone, mobilePhone, student);
        return "createdGuardian";
    }
}
