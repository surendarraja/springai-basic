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

    public String askAI(String prompt) {
    	try {
    		System.out.println("Prompt: " +prompt);
            return chatClient.call(prompt);
    	} catch(Exception ex) {
    		System.out.println("Prompt: " +ex);
    		ex.printStackTrace();
    		return "Exception";
    	}
    	
    }

    public String countryGuide(String country) {
        var template = """
                I want to visit the country  {country}.
                Tell me the top 3  places to visit.
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);

        Map<String, Object> params = Map.of("country", country);
        Prompt prompt = promptTemplate.create(params);

        return chatClient.call(prompt).getResult().getOutput().getContent();
    }

}
