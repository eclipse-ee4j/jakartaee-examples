package org.eclipse.ee4j.jakartashopbot.interfaces;

import java.io.IOException;

public interface ChatManagerObserver {
    void receiveChatResponse(String response) throws IOException;
}

