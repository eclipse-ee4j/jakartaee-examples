package jakarta.tutorial.web.faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class Hello {

    private String name;
    private String greeting;

    public void submit() {
        greeting = "Hello, " + name + "!";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getGreeting() {
        return greeting;
    }
}