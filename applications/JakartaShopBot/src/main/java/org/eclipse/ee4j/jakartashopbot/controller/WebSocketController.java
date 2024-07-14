package org.eclipse.ee4j.jakartashopbot.controller;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.eclipse.ee4j.jakartashopbot.interfaces.ChatManagerObserver;
import org.eclipse.ee4j.jakartashopbot.model.ChatManager;
import java.io.IOException;


@ServerEndpoint("/websocket")
public class WebSocketController implements ChatManagerObserver {

    @Inject
    private ChatManager chatManager;
    private Session session;

    @PostConstruct
    public void init(){
        chatManager.addChatManagerObserver(this);
    }

    @OnOpen
    public void onOpen(Session session) {

    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        this.session = session;
        chatManager.sendStreamingChatMessage(message);
        System.out.println("WebSocketController - Message received: " + message);
    }

    @Override
    public void receiveChatResponse(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }
}