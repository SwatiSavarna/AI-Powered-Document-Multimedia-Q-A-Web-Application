package com.ai.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AiClientService {

    private final RestTemplate restTemplate;

    @Value("${ai.service.url}")
    private String aiUrl;

    public String transcribe(Long fileId, String path) {
        Map<String, String> req = Map.of("filePath", path);
        return restTemplate.postForObject(
                aiUrl + "/transcribe/" + fileId,
                req,
                String.class
        );
    }

    public String askQuestion(Long fileId, String question) {
        Map<String, String> req = Map.of("question", question);
        return restTemplate.postForObject(
                aiUrl + "/chat/" + fileId,
                req,
                String.class
        );
    }

    public String summarize(Long fileId) {
        return restTemplate.getForObject(
                aiUrl + "/summarize/" + fileId,
                String.class
        );
    }
}