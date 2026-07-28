package com.example.MaupinAirlineTicketSystem.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller responsible for handling AI Chat requests for Maubin International Airline.
 */
@RestController
@RequestMapping("/airline/api")
public class AiChatController {

    private final WebClient webClient;

    @Value("${gemini.api.key}")
    private String apiKey;

    public AiChatController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        String userMessage = request.get("message");
        Map<String, String> response = new HashMap<>();

        if (userMessage == null || userMessage.trim().isEmpty()) {
            response.put("reply", "မေးခွန်းကို ရေးသားပေးပါခင်ဗျာ။");
            return ResponseEntity.ok(response);
        }

        try {
            String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-3.5-flash:generateContent?key=" + apiKey.trim();

            Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                    Map.of(
                        "role", "user",
                        "parts", List.of(
                            Map.of("text", "You are a customer service assistant for Maubin International Airline. Please answer politely in Myanmar language. User question: " + userMessage)
                        )
                    )
                )
            );

            Map<?, ?> geminiResponse = webClient.post()
                    .uri(url)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    // Retries on both HTTP 429 (Rate Limit) and HTTP 503 (Server Unavailable)
                    .retryWhen(Retry.backoff(3, Duration.ofSeconds(3))
                            .filter(throwable -> throwable instanceof WebClientResponseException statusEx &&
                                    (statusEx.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS || 
                                     statusEx.getStatusCode() == HttpStatus.SERVICE_UNAVAILABLE))
                    )
                    .block();

            String aiReply = extractTextFromGeminiResponse(geminiResponse);
            response.put("reply", aiReply);

        } catch (WebClientResponseException.ServiceUnavailable e) {
            System.err.println("Gemini Service Unavailable (503): " + e.getResponseBodyAsString());
            response.put("reply", "လက်ရှိအချိန်တွင် Gemini Server အားလုံး အလုပ်ရှုပ်နေပါသဖြင့် စက္ကန့်အနည်းငယ်အကြာတွင် ပြန်လည် စမ်းသပ်ပေးပါခင်ဗျာ။");
        } catch (WebClientResponseException.TooManyRequests e) {
            System.err.println("Gemini Rate Limit Exceeded (429): " + e.getResponseBodyAsString());
            response.put("reply", "မေးမြန်းမှု အရေအတွက် များပြားနေပါသဖြင့် စက္ကန့်အနည်းငယ် စောင့်ဆိုင်းပြီး ပြန်လည် မေးမြန်းပေးပါခင်ဗျာ။");
        } catch (WebClientResponseException e) {
            System.err.println("Gemini API WebClient Error: " + e.getResponseBodyAsString());
            response.put("reply", "API Error (" + e.getStatusCode() + "): " + e.getResponseBodyAsString());
        } catch (Exception e) {
            System.err.println("Execution Error: " + e.getMessage());
            e.printStackTrace();
            response.put("reply", "စနစ်ချို့ယွင်းချက်ဖြစ်နေပါသည်: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @SuppressWarnings("unchecked")
    private String extractTextFromGeminiResponse(Map<?, ?> response) {
        try {
            if (response != null && response.containsKey("candidates")) {
                List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
                if (candidates != null && !candidates.isEmpty()) {
                    Map<String, Object> candidate = candidates.get(0);
                    Map<String, Object> content = (Map<String, Object>) candidate.get("content");
                    List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
                    if (parts != null && !parts.isEmpty()) {
                        return (String) parts.get(0).get("text");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("JSON Parsing Error: " + e.getMessage());
        }
        return "အဖြေမရရှိနိုင်ပါ။";
    }
}