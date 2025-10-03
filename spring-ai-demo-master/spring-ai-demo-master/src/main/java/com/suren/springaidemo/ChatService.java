package com.suren.springaidemo;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String queryAi(String prompt) {
    	try {
    		System.out.println("Prompt: " +prompt);
            return chatClient.call(prompt);
    	} catch(Exception ex) {
    		System.out.println("Prompt: " +ex);
    		ex.printStackTrace();
    		return "Exception";
    	}
    	
    }

    public String getCityGuide(String city, String interest) {
        var template = """
                I am a tourist visiting the city  {city}.
                I am  interested in {interest}.
                Give me tips on what to do there.""";

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> params = Map.of("city", city, "interest", interest);
        Prompt prompt = promptTemplate.create(params);

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

}
