package org.eclipse.ee4j.jakartashopbot.interfaces;

import java.io.IOException;

public interface ChatServiceObserver {
    void receiveChatResponse(String responseToken) throws IOException;
}
