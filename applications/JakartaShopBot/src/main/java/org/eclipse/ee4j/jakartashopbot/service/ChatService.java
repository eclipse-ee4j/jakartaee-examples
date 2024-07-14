package org.eclipse.ee4j.jakartashopbot.service;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.model.StreamingResponseHandler;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.chat.StreamingChatLanguageModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.model.output.Response;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.ee4j.jakartashopbot.interfaces.ChatServiceObserver;
import org.eclipse.ee4j.jakartashopbot.model.ChatMessage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ChatService {

    List<ChatServiceObserver> chatServiceObservers = new ArrayList<>();
    String apiKey = System.getProperty("OPENAI_API_KEY");
    ChatLanguageModel chatModel = OpenAiChatModel.withApiKey(apiKey);
    StreamingChatLanguageModel streamingModel = OpenAiStreamingChatModel.withApiKey(apiKey);

    public void blockChat(String message){
        ChatMessage cMessage = new ChatMessage(message,"");
        String response = chatModel.generate(cMessage.getMessage());
        cMessage.setResponse(response);
    }

    public void streamingChat(String message){
        streamingModel.generate(message,new StreamingResponseHandler<AiMessage>() {

            @Override
            public void onNext(String token) {
                try {
                    notifyObservers(token);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onComplete(Response<AiMessage> response) {
                System.out.println("onComplete: " + response);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });

    }

    public void addChatServiceObserver(ChatServiceObserver observer) {
        chatServiceObservers.add(observer);
    }

    public void notifyObservers(String responseToken) throws IOException {
        for(ChatServiceObserver obs: chatServiceObservers){
            obs.receiveChatResponse(responseToken);
        }
    }

}
