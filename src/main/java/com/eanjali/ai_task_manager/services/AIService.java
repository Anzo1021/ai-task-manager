package com.eanjali.ai_task_manager.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AIService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public Map<String, String> analyzeTask(String taskTitle) {

        String prompt = """
                Analyze this task: '%s'
                
                Return ONLY a valid JSON object with exactly these 3 fields:
                {
                  "summary": "one clear sentence describing the task",
                  "priority": "HIGH or MEDIUM or LOW",
                  "category": "one word like Academic, Personal, Work, Finance, Health"
                }
                
                No extra text. No explanation. Just the JSON.
                """.formatted(taskTitle);

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key=" + apiKey;

        // ✅ ADD THIS - check key is loaded
        System.out.println("🔑 API Key present: " + (apiKey != null && !apiKey.isEmpty()));
        System.out.println("🌐 Calling URL: " + url);

        Map<String, Object> part = Map.of("text", prompt);
        Map<String, Object> content = Map.of("parts", List.of(part));
        Map<String, Object> requestBody = Map.of("contents", List.of(content));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // ✅ ADD THIS - see raw Gemini response
            System.out.println("📨 Raw Gemini response: " + response.getBody());

            JsonNode root = objectMapper.readTree(response.getBody());
            String aiContent = root
                    .path("candidates")
                    .get(0)
                    .path("content")
                    .path("parts")
                    .get(0)
                    .path("text")
                    .asText();

            aiContent = aiContent
                    .replace("```json", "")
                    .replace("```", "")
                    .trim();

            System.out.println("✅ Gemini response: " + aiContent);

            JsonNode aiJson = objectMapper.readTree(aiContent);

            Map<String, String> result = new HashMap<>();
            result.put("summary", aiJson.path("summary").asText("No summary available"));
            result.put("priority", aiJson.path("priority").asText("MEDIUM"));
            result.put("category", aiJson.path("category").asText("General"));
            return result;

        } catch (Exception e) {
            // ✅ IMPROVED - see exact error
            System.err.println(" Gemini API Error: " + e.getMessage());
            e.printStackTrace();
            Map<String, String> fallback = new HashMap<>();
            fallback.put("summary", "Could not generate AI summary.");
            fallback.put("priority", "MEDIUM");
            fallback.put("category", "General");
            return fallback;
        }
    }
}