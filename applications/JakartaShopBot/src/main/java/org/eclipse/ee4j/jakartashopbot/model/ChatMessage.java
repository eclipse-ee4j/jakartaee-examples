package org.eclipse.ee4j.jakartashopbot.model;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class ChatMessage {

    private String message;
    private String response;


    public ChatMessage(String message, String response) {
        this.message = message;
        this.response = response;
    }

    public ChatMessage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
