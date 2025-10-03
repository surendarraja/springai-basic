package com.suren.springaidemo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.image.ImageResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class AiController {

    private final ChatService chatService;
    private final ImageService imageService;

    public AiController(ChatService chatService, ImageService imageService) {
        this.chatService = chatService;
        this.imageService = imageService;
    }

    @GetMapping("create-image")
    public void createImage(HttpServletResponse response, @RequestParam("image") String image) throws IOException {
        ImageResponse imageResponse = imageService.createImage(image);

        // Get URL of the generated image
        String imageUrl = imageResponse.getResult().getOutput().getUrl();

        // Send redirect to the image URL
        response.sendRedirect(imageUrl);
    }

    @GetMapping("ask-the-ai")
    public String askAi(@RequestParam("prompt") String prompt){
    	System.out.println("Prompt: " +prompt);
        return chatService.askAI(prompt);
    }


    @GetMapping("country-guide")
    public String countryGuide(@RequestParam("country") String country) {
        return chatService.countryGuide(country);
    }
}
