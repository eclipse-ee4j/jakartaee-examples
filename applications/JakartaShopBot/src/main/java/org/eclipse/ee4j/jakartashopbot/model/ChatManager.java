package org.eclipse.ee4j.jakartashopbot.model;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.ee4j.jakartashopbot.interfaces.ChatManagerObserver;
import org.eclipse.ee4j.jakartashopbot.interfaces.ChatServiceObserver;
import org.eclipse.ee4j.jakartashopbot.service.ChatService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ApplicationScoped
public class ChatManager implements ChatServiceObserver {

    @Inject
    ChatService chatService;
    List<ChatManagerObserver> chatManagerObservers = new ArrayList<>();


    @PostConstruct
    public void init() {
        chatService.addChatServiceObserver(this);
    }

    public void sendStreamingChatMessage(String message) {
        chatService.streamingChat(message);
    }

    public void addChatManagerObserver(ChatManagerObserver chatManagerObserver) {
        this.chatManagerObservers.add(chatManagerObserver);
    }

    @Override
    public void receiveChatResponse(String token) throws IOException {
        for(var obs: chatManagerObservers) {
            obs.receiveChatResponse(token);
        }

    }

}
