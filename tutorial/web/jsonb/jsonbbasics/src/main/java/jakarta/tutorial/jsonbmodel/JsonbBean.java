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
package jakarta.tutorial.jsonbmodel;

import java.io.Serializable;
import java.util.ArrayList;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.tutorial.jsonbmodel.person.Person;
import jakarta.tutorial.jsonbmodel.person.PhoneNumber;

/**
 * This class manages the data from a JSF form, creates Object and serialize this object to JSON.
 * This class also deserialize JSON to object.
 *
 * @author David Kral
 */
@Named
@SessionScoped
public class JsonbBean implements Serializable {
    
    private static final long serialVersionUID = 5427226765445840012L;
    
    /* Form properties */
    protected static final String PHONE_TYPE_HOME = "Home";
    protected static final String PHONE_TYPE_WORK = "Work";

    private String name = "Jason Bourne";
    private String profession = "Super agent";

    private String phoneType1 = PHONE_TYPE_HOME;
    private String number1 = "123-456-789";

    private String phoneType2 = PHONE_TYPE_WORK;
    private String number2 = "123-555-555";

    protected String jsonTextArea = "";

    private Jsonb jsonb;
    
    public JsonbBean() {

        /* JsonbConfig and Jsonb instance creation */
        JsonbConfig config = new JsonbConfig()
                .withFormatting(true);
        this.jsonb = JsonbBuilder.create(config);
    }
    
    /* Getters and setters */
    public String getPhoneTypeHome() {
        return PHONE_TYPE_HOME;
    }

    public String getPhoneTypeWork() {
        return PHONE_TYPE_WORK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhoneType1() {
        return phoneType1;
    }

    public void setPhoneType1(String phoneType1) {
        this.phoneType1 = phoneType1;
    }

    public String getNumber1() {
        return number1;
    }

    public void setNumber1(String number1) {
        this.number1 = number1;
    }

    public String getPhoneType2() {
        return phoneType2;
    }

    public void setPhoneType2(String phoneType2) {
        this.phoneType2 = phoneType2;
    }

    public String getNumber2() {
        return number2;
    }

    public void setNumber2(String number2) {
        this.number2 = number2;
    }

    public String getJsonTextArea() {
        return jsonTextArea;
    }

    public void setJsonTextArea(String jsonTextArea) {
        this.jsonTextArea = jsonTextArea;
    }
    
    /**
     *  Action method for the form in index.xhtml.
     *  Creates Person object and creates formatted JSON
     */
    public String createJson() {
        Person person = new Person(this.name, this.profession, new ArrayList<>());
        person.getPhoneNumbers().add(new PhoneNumber(this.phoneType1, this.number1));
        person.getPhoneNumbers().add(new PhoneNumber(this.phoneType2, this.number2));

        /* Serialization to JSON */
        this.jsonTextArea = jsonb.toJson(person);
        
        /* JSF navigation */
        return "jsongenerated";
    }
    
    /**
     * Action method for form in jsongenerated.xhtml.
     * Deserialize JSON from the textarea to Person object
     * and fills these data to form.
     */
    public String parseJson() {
        clearFields();

        /* Deserialization of JSON to Object */
        Person person = jsonb.fromJson(this.jsonTextArea, Person.class);
        this.name = person.getName();
        this.profession = person.getProfession();
        if (person.getPhoneNumbers() != null) {
            int index = 0;
            for (PhoneNumber number : person.getPhoneNumbers()) {
                if (index == 0) {
                    this.number1 = number.getNumber();
                    this.phoneType1 = number.getType();
                } else if (index == 1) {
                    this.number2 = number.getNumber();
                    this.phoneType2 = number.getType();
                } else {
                    break;
                }
                index++;
            }
        }

        /* JSF navigation */
        return "index";
    }

    private void clearFields() {
        this.name = "";
        this.profession = "";
        this.number1 = "";
        this.phoneType1 = "";
        this.number2 = "";
        this.phoneType2 = "";
    }

}
